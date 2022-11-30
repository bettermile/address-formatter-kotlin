package net.placemarkt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

enum Templates {
  WORLDWIDE("worldwide.json"),
  COUNTRY_NAMES("countryNames.json"),
  ALIASES("aliases.json"),
  ABBREVIATIONS("abbreviations.json"),
  COUNTRY_2_LANG("country2Lang.json"),
  COUNTY_CODES("countyCodes.json"),
  STATE_CODES("stateCodes.json");

  private interface Constants {
    ObjectMapper jsonWriter = new ObjectMapper();
  }

  private final JsonNode data;

  Templates(String fileName) {
    this.data = setData(fileName);
  }

  public JsonNode getData() {
    return this.data;
  }

  private static JsonNode setData(String fileName) {
    JsonNode node = null;
    try {
      ClassLoader cl = Thread.currentThread().getContextClassLoader();
      InputStream is = Objects.requireNonNull(cl).getResourceAsStream(fileName);
      node = Constants.jsonWriter.readTree(is);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return node;
  }
}
