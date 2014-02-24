package io.github.repir.Strategy.Tools;

import io.github.repir.tools.Lib.Log;
import io.github.repir.tools.Stemmer.englishStemmer;
import java.util.HashSet;

/**
 * Stop word list of 429 terms from http://www.lextek.com/manuals/onix/stopwords1.html
 * which is the original list of stop words Salton & Buckley orginally used for
 * the SMART system at Cornell University, which was slightly trimmed down.
 */
public class StopWordsContractions {
   public static Log log = new Log( StopWordsContractions.class );
   
   public static String filterarray[] = {
      // removed "us", because we tokenize U.S. as us.
      "ain't", "aren't", "can't", "can't've", "'cause", "could've",
      "couldn't", "couldn't've", "didn't", "doesn't", "don't",
      "hadn't", "hadn't've", "hain't", "hasn't", "haven't", "he'd",
      "he'd've", "he'll", "he'll've", "he's", "how'd", "how'd'y", 
      "how'll", "how's", "i'd", "i'd've", "i'll", "i'll've", "i'm",
      "i've", "isn't", "it'd", "it'd've", "it'll", "it'll've", "it's",
      "let's", "ma'am", "mayn't", "might've", "mightn't", "mightn't've",
      "must've", "mustn't", "mustn't've", "needn't", "needn't've", "oughtn't",
      "oughtn't've", "shalln't", "shan't", "sha'n't", "shan't've", "she'd", 
      "she'd've", "she'll", "she'll've", "she's", "should've", "shouldn't",
      "shouldn't've", "so've", "so's", "that'd", "that'd've", "that's",
      "there'd", "there'd've", "there's", "they'd", "they'd've", "they'll",
      "they'll've", "they're", "they've", "to've", "wasn't", "we'd", "we'd've",
      "we'll", "we'll've", "we're", "we've", "weren't", "what'll", "what'll've",
      "what're", "what's", "what've", "when's", "when've", "where'd",
      "where's", "where've", "who'll", "who'll've", "who's", "who've",
      "why's", "why've", "will've", "won't", "won't've", "would've", "wouldn't",
      "wouldn't've", "y'all", "y'all'd", "y'all'd've", "y'all're", 
      "y'all've", "you'd", "you'd've", "you'll", "you'll've", "you're",
      "you've"
   };

   public static HashSet<String> getUnstemmedFilterSet() {
      HashSet<String> set = new HashSet<String>();
      for (String s : filterarray) {
         set.add(s);
      }
      return set;
   }
}
