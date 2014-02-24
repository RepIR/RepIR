package io.github.repir.Extractor.Tools;

import io.github.repir.tools.Lib.Log;
import io.github.repir.Extractor.Entity;
import io.github.repir.Extractor.EntityAttribute;
import io.github.repir.Extractor.Extractor;
import io.github.repir.tools.DataTypes.ByteArrayPos;

/**
 * Trims Literal tokens, by reducing inter-string multiple whitespace characters
 * to single spaces.
 * <p/>
 * @author jeroen
 */
public class TrimLiteralTokens extends ExtractorProcessor {

   private static Log log = new Log(TrimLiteralTokens.class);

   public TrimLiteralTokens(Extractor extractor, String process) {
      super(extractor, process);
   }

   @Override
   public void process(Entity entity, Entity.SectionPos section, String attributename) {
      EntityAttribute attribute = entity.get(attributename);
      if (attribute.size() > 0) {
         StringBuilder sb = new StringBuilder();
         for (String chunk : attribute) {
            String s = chunk.toString().trim();
            if (s.length() > 0)
               sb.append(" ").append(s);
         }
         attribute.clear();
         if (sb.length() > 0) {
            String first = sb.deleteCharAt(0).toString().replaceAll("\\s{2,}|\\n", " ");
            attribute.add(first);
         } else {
            attribute.add("");
         }
      }
   }
}