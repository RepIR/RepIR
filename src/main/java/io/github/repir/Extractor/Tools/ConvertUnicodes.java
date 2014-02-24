package io.github.repir.Extractor.Tools;

import io.github.repir.Extractor.Extractor;
import io.github.repir.tools.Lib.Log;
import io.github.repir.tools.Lib.StrTools;

/**
 * Converts unicode characters such as 'decorated' vowels to their corresponding
 * ASCII character.
 * <p/>
 * @author jbpvuurens
 */
public class ConvertUnicodes extends Translator {

   public static Log log = new Log(ConvertUnicodes.class);

   public ConvertUnicodes(Extractor extractor, String process) {
      super(extractor, process);
      int i;
      for (i = 0; i < StrTools.unicodebyteC3.length; i++) {
         byte search[] = new byte[2];
         byte replace[] = new byte[2];
         search[0] = (byte) 0xC3;
         replace[0] = 0;
         search[1] = StrTools.unicodebyteC3[i];
         replace[1] = StrTools.asciibyteC3[i];
         this.add(search, replace);
      }
      for (i = 0; i < StrTools.unicodebyteC5.length; i++) {
         byte search[] = new byte[2];
         byte replace[] = new byte[2];
         search[0] = (byte) 0xC5;
         replace[0] = 0;
         search[1] = StrTools.unicodebyteC5[i];
         replace[1] = StrTools.asciibyteC5[i];
         this.add(search, replace);
      }
      for (i = 0; i < StrTools.asciiextendedbyte.length; i++) {
         byte search[] = new byte[1];
         byte replace[] = new byte[1];
         search[0] = StrTools.asciiextendedbyte[i];
         replace[0] = StrTools.asciibyte[i];
         this.add(search, replace);
      }
   }
}
