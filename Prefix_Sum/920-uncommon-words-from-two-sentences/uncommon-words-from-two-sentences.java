import java.util.*;

class Solution {
    public String[] uncommonFromSentences(String s1, String s2) {
        // Map to store the frequency of each word across both sentences
        Map<String, Integer> counts = new HashMap<>();
        
        // Process the first sentence
        for (String word : s1.split(" ")) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }
        
        // Process the second sentence
        for (String word : s2.split(" ")) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }
        
        List<String> result = new ArrayList<>();
        
        // Collect words that appeared exactly once
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == 1) {
                result.add(entry.getKey());
            }
        }
        
        // Convert the List back to a String array
        return result.toArray(new String[0]);
    }
}