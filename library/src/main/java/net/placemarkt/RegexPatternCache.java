package net.placemarkt;

import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;

class RegexPatternCache {
  private final Map<String, Pattern> map = new HashMap<>();

  public Pattern get(String key) {
    return map.computeIfAbsent(key, Pattern::compile);
  }
}
