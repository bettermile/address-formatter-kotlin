package net.placemarkt.yamlconverter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Transpiler {

  private interface Constants {
    ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
    YAMLFactory yamlFactory = new YAMLFactory();
    ObjectMapper jsonWriter = new ObjectMapper();
  }

  public static void main(String[] args) {
    Transpiler.transpileWorldwide();
    Transpiler.transpileCountryNames();
    Transpiler.transpileAliases();
    Transpiler.transpileAbbreviations();
    Transpiler.transpileCountry2Lang();
    Transpiler.transpileCountyCodes();
    Transpiler.transpileStateCodes();
    Transpiler.testCases();
  }

  static final String destinationDir = "library/src/main/resources/";
  static final String kotlinDestinationDir = "library/src/main/java/net/placemarkt/";

  static void transpileWorldwide() {
    try {
      Path path = Paths.get("address-formatting/conf/countries/worldwide.yaml");
      String yaml = readFile(path.toString());
      ObjectNode obj = (ObjectNode) Constants.yamlReader.readTree(yaml);
      try (PrintWriter out = new PrintWriter(kotlinDestinationDir + "Worldwide.kt")) {
        out.println("package net.placemarkt");
        out.println();
        out.println("internal object Workldwide {");
        boolean wroteMapStart = false;
        for (Map.Entry<String, JsonNode> entry : obj.properties()) {
          String key = entry.getKey();
          JsonNode value = entry.getValue();
          if (key.startsWith("generic") || key.startsWith("fallback")) {
            out.print("private const val ");
            out.print(key);
            out.print(" = \"\"\"");
            out.print(value.asText());
            out.println("\"\"\"");
          } else if (key.equals("default")) {
            out.print("val default = ");
            printCountryFormat(out, (ObjectNode) value);
          } else {
            if (!wroteMapStart) {
              out.println("val countries: Map<String, Lazy<CountryFormat>> = mapOf(");
              wroteMapStart = true;
            }
            out.print('\"');
            out.print(key);
            out.println("\" to lazy {");
            printCountryFormat(out, (ObjectNode) value);
            out.println("},");
          }
        }

        out.println(")");
        out.println("}");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void printCountryFormat(PrintWriter out, ObjectNode valueObject) {
    out.println("    CountryFormat(");
    printProperty(out, valueObject, "addressTemplate", "address_template");
    printProperty(out, valueObject, "fallbackTemplate", "fallback_template");
    printRegexProperty(out, valueObject, "replace", "replace");
    printRegexProperty(out, valueObject, "postformatReplace", "postformat_replace");
    printProperty(out, valueObject, "useCountry", "use_country");
    printProperty(out, valueObject, "changeCountry", "change_country");
    printProperty(out, valueObject, "addComponent", "add_component");
    out.println("    )");
  }

  private static void printProperty(
    PrintWriter out,
    ObjectNode valueObject,
    String propertyKotlinName,
    String propertyYamlName
  ) {
    if (valueObject.has(propertyYamlName)) {
      out.print("        ");
      out.print(propertyKotlinName);
      out.print(" = ");
      if (valueObject.has(propertyYamlName)) {
        String template = valueObject.get(propertyYamlName).asText();
        if (template.startsWith("generic") || template.startsWith("fallback")) {
          out.print(template);
        } else if (template.contains("\n")) {
          out.print("\"\"\"");
          out.print(makeSimpleTextConform(template));
          out.print("\"\"\"");
        } else {
          out.print('\"');
          out.print(makeSimpleTextConform(template));
          out.print('\"');
        }
      } else {
        out.print((String) null);
      }
      out.println(",");
    }
  }

  private static void printRegexProperty(
    PrintWriter out,
    ObjectNode valueObject,
    String propertyKotlinName,
    String propertyYamlName
  ) {
    if (valueObject.has(propertyYamlName)) {
      out.print("        ");
      out.print(propertyKotlinName);
      out.println(" = listOf(");
      valueObject.get(propertyYamlName).elements().forEachRemaining(jsonNode -> {
        String regex = makeRegexConform(jsonNode.get(0).asText());
        String replacement = makeRegexConform(jsonNode.get(1).asText());
        out.print("            CountryFormat.Replace(search = \"");
        out.print(regex);
        out.print("\", replacement = \"");
        out.print(replacement);
        out.println("\"),");
      });
      out.println("        ),");
    }
  }

  private static String makeRegexConform(String regex) {
    return regex.replace("\\", "\\\\").replace("\n", "\\n");
  }

  private static String makeSimpleTextConform(String regex) {
   return regex.replace("$", "\\$");
  }

  static void transpileCountryNames() {
    ObjectNode node = null;
    try {
      Path path = Paths.get("address-formatting/conf/country_codes.yaml");
      String yaml = Transpiler.readFile(path.toString());
      String formattedYaml = yaml.replaceAll(" # ", " ");
      Object obj = Constants.yamlReader.readValue(formattedYaml, Object.class);
      node = Constants.jsonWriter.valueToTree(obj);
      try (PrintWriter out = new PrintWriter(destinationDir + "countryNames.json")) {
        out.println(node.toString());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static void transpileAliases() {
    try {
      final ArrayNode node = Constants.jsonWriter.createArrayNode();
      Path path = Paths.get("address-formatting/conf/components.yaml");
      YAMLParser yamlParser = Constants.yamlFactory.createParser(path.toFile());
      List<ObjectNode> nodes = Constants.jsonWriter
          .readValues(yamlParser, new TypeReference<ObjectNode>() {})
          .readAll();

      nodes.forEach(component-> {
        ArrayNode aliases = component.withArray("aliases");
        aliases.forEach(alias -> {
          ObjectNode componentNode = node.addObject();
          componentNode.put("alias", alias.textValue());
          componentNode.put("name", component.get("name").textValue());
        });
        ObjectNode componentNode =  node.addObject();
        componentNode.put("alias", component.get("name").textValue());
        componentNode.put("name", component.get("name").textValue());
      });
      try (PrintWriter out = new PrintWriter(destinationDir + "aliases.json")) {
        out.println(node.toString());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static void transpileAbbreviations() {
    ObjectNode abbreviations = Constants.jsonWriter.createObjectNode();
    try {
      try (Stream<Path> paths = Files.list(Paths.get("address-formatting/conf/abbreviations"))) {
        paths.sorted(PATH_BY_NAME_COMPARATOR).forEach(path -> {
          try {
            String fileNameWithExtension = path.getFileName().toString();
            int pos = fileNameWithExtension.lastIndexOf(".");
            String fileName = fileNameWithExtension.substring(0, pos).toUpperCase();
            String yaml = Transpiler.readFile(path.toString());
            Object obj = Constants.yamlReader.readValue(yaml, Object.class);
            JsonNode country = Constants.jsonWriter.valueToTree(obj);
            Iterator<String> fieldName = country.fieldNames();
            ArrayNode countryComponentArray = Constants.jsonWriter.createArrayNode();
            while (fieldName.hasNext()) {
              String type = fieldName.next();
              JsonNode replacements = country.get(type);
              Iterator<String> srcs = replacements.fieldNames();
              ArrayNode pairs = Constants.jsonWriter.createArrayNode();
              while (srcs.hasNext()) {
                String src = srcs.next();
                String dest = replacements.get(src).textValue();
                ObjectNode pair = pairs.addObject();
                pair.put("src", src);
                pair.put("dest", dest);
              }
              ObjectNode countryComponent = Constants.jsonWriter.createObjectNode();
              countryComponent.put("component", type);
              countryComponent.set("replacements", pairs);
              countryComponentArray.add(countryComponent);
            }
            abbreviations.set(fileName, countryComponentArray);
          } catch (IOException e) {
            e.printStackTrace();
          }
        });
      }

      try (PrintWriter out = new PrintWriter(destinationDir + "abbreviations.json")) {
        out.println(abbreviations.toString());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void testCases() {
    try {
      ArrayNode rootNode = Constants.jsonWriter.createArrayNode();
      Stream<Path> paths = Stream.of(
              "address-formatting/testcases/countries",
                      "address-formatting/testcases/other"
              ).flatMap(path -> {
                try {
                  return Files.list(Paths.get(path));
                }catch (IOException e) {
                  throw new RuntimeException("error while reading path " + path, e);
                }
              });
      paths.sorted(PATH_BY_NAME_COMPARATOR).forEach(path -> {
        try {
          String yaml = readFile(path.toString());
          try (YAMLParser parser = Constants.yamlFactory.createParser(yaml)) {
            Constants.yamlReader.readValues(parser, Object.class).forEachRemaining(element -> {
              if (element != null) {
                ObjectNode node = Constants.jsonWriter.valueToTree(element);
                String description = node.get("description").asText();
                String fileName = path.getFileName().toString();
                node.put("description", fileName.substring(0, fileName.length() - 5) + " - " + description);
                rootNode.add(node);
              }
            });
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
      PrintWriter out = new PrintWriter("library/src/test/resources/countries.json");
      out.println(rootNode.toString());
      out.close();
    }catch (IOException e) {
      e.printStackTrace();
    }
  }

  static void transpileCountry2Lang() {
    ObjectNode node = null;
    try {
      Path path = Paths.get("address-formatting/conf/country2lang.yaml");
      String yaml = Transpiler.readFile(path.toString());
      Object obj = Constants.yamlReader.readValue(yaml, Object.class);
      node = Constants.jsonWriter.valueToTree(obj);
      Iterator<String> countries = node.fieldNames();
      while (countries.hasNext()) {
        String country = countries.next();
        String languages = node.get(country).asText();
        ArrayNode languagesArray = node.putArray(country);
        String[] languagesSplit = languages.split(",");
        for (String s : languagesSplit) {
          languagesArray.add(s.toUpperCase());
        }
      }
      try (PrintWriter out = new PrintWriter(destinationDir + "country2Lang.json")) {
        out.println(node.toString());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /*
  TODO: Look into formatting this data in such a way that makes it easier to query
   */
  static void transpileCountyCodes() {
    ObjectNode node = null;
    try {
      Path path = Paths.get("address-formatting/conf/county_codes.yaml");
      String yaml = Transpiler.readFile(path.toString());
      Object obj = Constants.yamlReader.readValue(yaml, Object.class);
      node = Constants.jsonWriter.valueToTree(obj);
      try (PrintWriter out = new PrintWriter(destinationDir + "countyCodes.json")) {
        out.println(node.toString());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /*
  TODO: Look into formatting this data in such a way that makes it easier to query
   */
  static void transpileStateCodes() {
    ObjectNode node = null;
    try {
      Path path = Paths.get("address-formatting/conf/state_codes.yaml");
      String yaml = Transpiler.readFile(path.toString());
      Object obj = Constants.yamlReader.readValue(yaml, Object.class);
      node = Constants.jsonWriter.valueToTree(obj);
      try (PrintWriter out = new PrintWriter(destinationDir + "stateCodes.json")) {
        out.println(node.toString());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static String readFile(String path)
      throws IOException
  {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return new String(encoded, StandardCharsets.UTF_8);
  }

  static final Comparator<Path> PATH_BY_NAME_COMPARATOR =
          Comparator.comparing((Path path) -> path.getFileName().toString());
}
