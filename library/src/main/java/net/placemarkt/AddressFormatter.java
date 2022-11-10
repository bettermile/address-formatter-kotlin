package net.placemarkt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.mustachejava.*;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.io.StringWriter;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.HashMap;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Function;
import com.google.common.base.CaseFormat;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.Optional;
import java.util.ArrayList;

public class AddressFormatter {

  private static final RegexPatternCache regexPatternCache = new RegexPatternCache();
  private static final List<String> knownComponents = AddressFormatter.getKnownComponents();
  private static final Map<String, String> replacements = new HashMap<>() {{
    put("[\\},\\s]+$", "");
    put("^[,\\s]+", "");
    put("^- ", "");
    put(",\\s*,", ", ");
    put("[ \t]+,[ \t]+", ", ");
    put("[ \t][ \t]+", " ");
    put("[ \t]\n", "\n");
    put("\n,", "\n");
    put(",+", ",");
    put(",\n", "\n");
    put("\n[ \t]+", "\n");
    put("\n+", "\n");
  }};

  private final ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
  private final boolean abbreviate;
  private final boolean appendCountry;

  public AddressFormatter(Boolean abbreviate, Boolean appendCountry) {
    this.abbreviate = abbreviate;
    this.appendCountry = appendCountry;
  }

  public String format(String json) throws IOException {
    return format(json, null);
  }

  public String format(String json, String fallbackCountryCode) throws IOException {
    TypeFactory factory = TypeFactory.defaultInstance();
    MapType type = factory.constructMapType(HashMap.class, String.class, String.class);
    Map<String, String> components;

    try {
       components = yamlReader.readValue(json, type);
    } catch (JsonProcessingException e) {
      throw new IOException("Json processing exception", e);
    }
    components = normalizeFields(components);

    components = determineCountryCode(components, fallbackCountryCode);
    String countryCode = Objects.requireNonNull(components.get("country_code"));

    if (appendCountry && Templates.COUNTRY_NAMES.getData().has(countryCode) && components.get("country") == null) {
      components.put("country", Templates.COUNTRY_NAMES.getData().get(countryCode).asText());
    }

    components = applyAliases(components);
    JsonNode template = findTemplate(components);
    components = cleanupInput(components, template.get("replace"));
    return renderTemplate(template, components);
  }

  Map<String, String> normalizeFields(Map<String, String> components) {
    Map<String, String> normalizedComponents = new HashMap<>();
    for (Map.Entry<String, String> entry : components.entrySet()) {
      String field = entry.getKey();
      String value = entry.getValue();
      String newField = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field);
      if (!normalizedComponents.containsKey(newField)) {
        normalizedComponents.put(newField, value);
      }
    }
    return normalizedComponents;
  }

  Map<String, String> determineCountryCode(Map<String, String> components,
    String fallbackCountryCode) {
    String countryCode = components.get("country_code");

    if (invalidCountryCode(countryCode)) {
      if (invalidCountryCode(fallbackCountryCode)) {
        throw new Error("No country code provided. Use fallbackCountryCode?");
      }
      countryCode = fallbackCountryCode;
    }

    // can't be null, so can be ignored
    //noinspection ConstantConditions
    countryCode = countryCode.toUpperCase();

    if (countryCode.equals("UK")) {
      countryCode = "GB";
    }

    JsonNode country = Templates.WORLDWIDE.getData().get(countryCode);
    if (country != null && country.has("use_country")) {
      String oldCountryCode = countryCode;
      countryCode = country.get("use_country").asText().toUpperCase();

      if (country.has("change_country")) {
        String newCountry = country.get("change_country").asText();
        Pattern p = regexPatternCache.get("\\$(\\w*)");
        Matcher m = p.matcher(newCountry);
        String match;
        if (m.find()) {
          match = m.group(1); // $state
          Pattern p2 = regexPatternCache.get(String.format("\\$%s", match));
          Matcher m2;
          String toReplace = components.get(match);
          m2 = p2.matcher(newCountry);
          newCountry = m2.replaceAll(Objects.requireNonNullElse(toReplace, ""));
        }
        components.put("country", newCountry);
      }

      JsonNode oldCountry = Templates.WORLDWIDE.getData().get(oldCountryCode);
      JsonNode oldCountryAddComponent = oldCountry.get("add_component");
      if (oldCountryAddComponent != null) {
        String completeText = oldCountryAddComponent.textValue();
        int assignIndex = completeText.indexOf('=');
        if (assignIndex > 0 && "state".equals(completeText.substring(0, assignIndex))) {
          components.put("state", completeText.substring(assignIndex + 1));
        }
      }
    }

    String state = components.get("state");

    if (countryCode.equals("NL") && state != null) {
      if (state.equals("Curaçao")) {
        countryCode = "CW";
        components.put("country", "Curaçao");
      } else if (state.equalsIgnoreCase("sint maarten")) {
        countryCode = "SX";
        components.put("country", "Sint Maarten");
      } else if (state.equalsIgnoreCase("aruba")) {
        countryCode = "AW";
        components.put("country", "Aruba");
      }
    }

    components.put("country_code", countryCode);
    return components;
  }

  boolean invalidCountryCode(String countryCode) {
    return countryCode == null || countryCode.length() != 2 || !Templates.WORLDWIDE.getData().has(countryCode.toUpperCase());
  }

  Map<String, String> cleanupInput(Map<String, String> components, JsonNode replacements) {
    String country = components.get("country");
    String state = components.get("state");

    //noinspection UnstableApiUsage
    if (country != null && state != null && Ints.tryParse(country) != null) {
      components.put("country", state);
      components.remove("state");
    }
    if (replacements != null && replacements.size() > 0) {
      for (Entry<String, String> componentEntry : components.entrySet()) {
        String component = componentEntry.getKey();
        String componentValue = componentEntry.getValue();
        Iterator<JsonNode> rIterator = replacements.iterator();
        String regex = String.format("^%s=", component);
        Pattern p = regexPatternCache.get(regex);
        while (rIterator.hasNext()) {
          ArrayNode replacement = (ArrayNode) rIterator.next();
          Matcher m = p.matcher(replacement.get(0).asText());
          if (m.find()) {
            m.reset();
            String value = m.replaceAll("");
            if (componentValue.equals(value)) {
              componentValue = replacement.get(1).asText();
              componentEntry.setValue(componentValue);
            }
            m.reset();
          } else {
            Pattern p2 = regexPatternCache.get(replacement.get(0).asText());
            Matcher m2 = p2.matcher(componentValue);
            componentValue = m2.replaceAll(replacement.get(1).asText());
            m.reset();
            componentEntry.setValue(componentValue);
          }
        }
      }
    }

    String stateValue = components.get("state");
    if (!components.containsKey("state_code")  && stateValue != null) {
      String stateCode = getStateCode(stateValue, Objects.requireNonNull(components.get("country_code")));
      components.put("state_code", stateCode);
      Pattern p = regexPatternCache.get("^washington,? d\\.?c\\.?");
      Matcher m = p.matcher(stateValue);
      if (m.find()) {
        components.put("state_code", "DC");
        components.put("state", "District of Columbia");
        components.put("city", "Washington");
      }
    }

    String county = components.get("county");
    if (!components.containsKey("county_code") && county != null) {
      String countyCode = getCountyCode(county, Objects.requireNonNull(components.get("country_code")));
      components.put("county_code", countyCode);
    }

    List<String> unknownComponents = components.entrySet().stream().filter(component -> {
      if (component.getKey() == null) {
        return false;
      }
      return !knownComponents.contains(component.getKey());
    }).map(Entry::getValue).collect(Collectors.toList());

    if (unknownComponents.size() > 0) {
      components.put("attention", String.join(", ", unknownComponents));
    }


    String postCode = components.get("postcode");
    if (postCode != null) {
      Pattern p1 = regexPatternCache.get("^(\\d{5}),\\d{5}");
      Pattern p2 = regexPatternCache.get("\\d+;\\d+");
      Matcher m1 = p1.matcher(postCode);
      Matcher m2 = p2.matcher(postCode);
      if (postCode.length() > 20) {
        components.remove("postcode");
      } else if (m2.matches()) {
        components.remove("postcode");
      } else if (m1.matches()) {
        components.put("postcode", m1.group(1));
      }
    }

    String countryCode = components.get("country_code");
    if (abbreviate && countryCode != null && Templates.COUNTRY_2_LANG.getData().has(countryCode)) {
      JsonNode languages = Templates.COUNTRY_2_LANG.getData().get(countryCode);
      StreamSupport.stream(languages.spliterator(), false)
          .filter(language -> Templates.ABBREVIATIONS.getData().has(language.textValue()))
          .map(language -> Templates.ABBREVIATIONS.getData().get(language.textValue())).forEach(
          languageAbbreviations -> StreamSupport.stream(languageAbbreviations.spliterator(), false)
              .filter(abbreviation -> abbreviation.has("component"))
              .forEach(abbreviation -> StreamSupport.stream(abbreviation.get("replacements").spliterator(), false)
                  .forEach(replacement -> {
                    String key = abbreviation.get("component").asText();
                    if (key == null) {
                      return;
                    }
                    String value = components.get(key);
                    if (value == null) {
                      return;
                    }
                    String src = replacement.get("src").asText();
                    String regex = String.format("\\b%s\\b", src);
                    Pattern p = regexPatternCache.get(regex);
                    Matcher m = p.matcher(value);
                    String newComponent = m.replaceAll(replacement.get("dest").asText());
                    components.put(key, newComponent);
                  })));
    }

    Pattern p = regexPatternCache.get("^https?://");
    return components.entrySet().stream().filter(component -> {
      if (component.getValue() == null) {
        return false;
      }

      Matcher m = p.matcher(component.getValue());

      if (m.matches()) {
        m.reset();
        return false;
      }

      m.reset();
      return true;
    }).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
  }

  Map<String, String> applyAliases(Map<String, String> components) {
    Map<String, String> aliasedComponents = new HashMap<>();
    components.forEach((key, value) -> {
      String newKey = key;
      Iterator<JsonNode> iterator = Templates.ALIASES.getData().elements();
      while (iterator.hasNext()) {
        JsonNode pair = iterator.next();
        if (pair.get("alias").asText().equals(key)
            && components.get(pair.get("name").asText()) == null) {
          newKey = pair.get("name").asText();
          break;
        }
      }
      aliasedComponents.put(key, value);
      aliasedComponents.put(newKey, value);
    });

    return aliasedComponents;
  }

  JsonNode findTemplate(Map<String, String> components) {
    JsonNode template;
    String country_code = components.get("country_code");
    if (country_code != null && Templates.WORLDWIDE.getData().has(country_code)) {
      template = Templates.WORLDWIDE.getData().get(country_code);
    } else {
      template = Templates.WORLDWIDE.getData().get("default");
    }

    return template;
  }

  JsonNode chooseTemplateText(JsonNode template, Map<String, String> components) {
    JsonNode selected;
    if (template.has("address_template")) {
      if (Templates.WORLDWIDE.getData().has(template.get("address_template").asText())) {
        selected = Templates.WORLDWIDE.getData().get(template.get("address_template").asText());
      } else {
        selected = template.get("address_template");
      }
    } else {
      JsonNode defaults = Templates.WORLDWIDE.getData().get("default");
      selected = Templates.WORLDWIDE.getData().get(defaults.get("address_template").textValue());
    }

    List<String> required = Arrays.asList("road", "postcode");
    long count = required.stream().filter(req -> !components.containsKey(req)).count();
    if (count == 2) {
      if (template.has("fallback_template")) {
        if (Templates.WORLDWIDE.getData().has(template.get("fallback_template").asText())) {
          selected = Templates.WORLDWIDE.getData().get(template.get("fallback_template").asText());
        } else {
          selected = template.get("fallback_template");
        }
      } else {
        JsonNode defaults = Templates.WORLDWIDE.getData().get("default");
        selected = Templates.WORLDWIDE.getData().get(defaults.get("fallback_template").textValue());
      }
    }
    return selected;
  }

  String getStateCode(String state, String countryCode) {
    if (!Templates.STATE_CODES.getData().has(countryCode)) {
      return null;
    }

    JsonNode countryCodes = Templates.STATE_CODES.getData().get(countryCode);
    Iterator<String> iterator = countryCodes.fieldNames();
    return StreamSupport
        .stream(Spliterators.spliteratorUnknownSize(iterator,
        Spliterator.ORDERED), false).filter(key-> {
          JsonNode code = countryCodes.get(key);
      if (code.isObject()) {
        if (code.has("default")) {
          return code.get("default").asText().equalsIgnoreCase(state);
        }
      } else {
        return code.asText().equalsIgnoreCase(state);
      }
      return false;
    }).findFirst().orElse(null);
  }

  String getCountyCode(String county, String countryCode) {
    if (!Templates.COUNTY_CODES.getData().has(countryCode)) {
      return null;
    }
    JsonNode country = Templates.COUNTY_CODES.getData().get(countryCode);
    Optional<JsonNode> countyCode = StreamSupport.stream(country.spliterator(), true).filter(posCounty -> {
      if (posCounty.isObject()) {
        if (posCounty.has("default")) {
          return posCounty.get("default").asText().equalsIgnoreCase(county);
        }
      } else {
        return posCounty.asText().equalsIgnoreCase(county);
      }
      return false;
    }).findFirst();

    return countyCode.map(JsonNode::asText).orElse(null);
  }

  String renderTemplate(JsonNode template, Map<String, String> components) {
    Map<String, Object> callback = new HashMap<>();
    callback.put("first", (Function<String, String>) s -> {
      String[] splitted = s.split("\\s*\\|\\|\\s*");
      Optional<String> chosen = Arrays.stream(splitted).filter(v -> v.length() > 0).findFirst();
      return chosen.orElse("");
    });

    JsonNode templateText = chooseTemplateText(template, components);
    MustacheFactory mf = new DefaultMustacheFactory();
    Mustache m = mf.compile(new StringReader(templateText.asText()), "example");
    StringWriter st = new StringWriter();
    m.execute(st, new Object[]{ components, callback});
    String rendered = cleanupRender(st.toString());

    if (template.has("postformat_replace")) {
      ArrayNode postformat = (ArrayNode) template.get("postformat_replace");
      for (JsonNode regex : postformat) {
        Pattern p = regexPatternCache.get(regex.get(0).asText());
        Matcher m2 = p.matcher(rendered);
        rendered = m2.replaceAll(regex.get(1).asText());
      }
    }
    rendered = cleanupRender(rendered);
    String trimmed = rendered.trim();

    return trimmed + "\n";
  }

  String cleanupRender(String rendered) {
    Set<Map.Entry<String, String>> entries = replacements.entrySet();
    String deduped = rendered;

    for(Map.Entry<String, String> replacement : entries) {
      Pattern p = regexPatternCache.get(replacement.getKey());
      Matcher m = p.matcher(deduped);
      String predupe = m.replaceAll(replacement.getValue());
      deduped = dedupe(predupe);
    }

    return deduped;
  }

  String dedupe(String rendered) {
     return Arrays.stream(rendered.split("\n"))
        .map(s -> Arrays.stream(s.trim().split(", "))
            .map(String::trim).distinct().collect(Collectors.joining(", ")))
        .distinct()
        .collect(Collectors.joining("\n"));
  }

  static List<String> getKnownComponents() {
    List<String> knownComponents = new ArrayList<>();
    Iterator<JsonNode> fields = Templates.ALIASES.getData().elements();
    while (fields.hasNext()) {
      JsonNode field = fields.next();
      knownComponents.add(field.get("alias").textValue());
    }

    return knownComponents;
  }
}
