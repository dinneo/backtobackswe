class WithHashtable {
  public int longestPalindrome(String s) {
    int longestPalindromeLength = 0;

    Map<Character, Integer> characterToCount = new HashMap<>();
    for (char c: s.toCharArray()) {
      if (!characterToCount.containsKey(c)) {
        characterToCount.put(c, 1);
      } else {
        characterToCount.put(c, characterToCount.get(c) + 1);
      }
    }

    Character mostFrequentOddCharacter = null;
    int mostFrequentOddCharacterFrequency = 0;
    for (Map.Entry<Character, Integer> entry: characterToCount.entrySet()) {
      char c = entry.getKey();
      int count = entry.getValue();

      if (count % 2 == 0) {
        longestPalindromeLength += count; // character can mirror itself
      } else {
        if (count > mostFrequentOddCharacterFrequency) {
          mostFrequentOddCharacter = c;
          mostFrequentOddCharacterFrequency = count;
        }
      }
    }

    /*
      Only place odd frequency character can happen is the middle, so
      we "place" the most frequent odd character in the middle
    */
    longestPalindromeLength += mostFrequentOddCharacterFrequency;

    /*
      Now we remove 1 from each of the rest of the odd characters to turn
      their frequency even
    */
    if (mostFrequentOddCharacter != null) {
      characterToCount.remove(mostFrequentOddCharacter);

      for (Map.Entry<Character, Integer> entry: characterToCount.entrySet()) {
        char c = entry.getKey();
        int count = entry.getValue();

        if (count % 2 != 0) {
          longestPalindromeLength += count - 1;
        }
      }
    }

    return longestPalindromeLength;
  }
}

class WithSet {
  public int longestPalindrome(String s) {
    int matchings = 0;
    Set<Character> unmatchedCharacters = new HashSet<Character>();

    for (char c: s.toCharArray()) {
      if (unmatchedCharacters.contains(c)) {
        unmatchedCharacters.remove(c);
        matchings++;
      } else {
        unmatchedCharacters.add(c);
      }
    }

    int longestPalindromeLength = matchings * 2;
    if (!unmatchedCharacters.isEmpty()) {
      longestPalindromeLength++; // place 1 unmatched odd character in the middle
    }

    return longestPalindromeLength;
  }
}
