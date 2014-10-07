package io.github.repir.Extractor.Tools;

import io.github.repir.tools.ByteSearch.ByteRegex;
import io.github.repir.Extractor.Entity;
import io.github.repir.Extractor.Extractor;
import io.github.repir.tools.ByteSearch.ByteSearchPosition;
import io.github.repir.tools.Lib.Log;
import java.util.ArrayList;

/**
 * Converts dots depending on the context. Does not filter out + and - which can
 * be useful in queries.
 * <p/>
 * @author jbpvuurens
 */
public class ConvertDotsQuery extends ExtractorProcessor {

   public static Log log = new Log(ConvertDotsQuery.class);
   ByteRegex number = new ByteRegex("\\.(^|(?<=[^\\w\\.]\\d\\.))\\d+(e[\\-\\+]?\\d+)?($|(?=[^\\w\\.]))");
   ByteRegex abbrev = new ByteRegex("\\.((?<=^\\c\\.)|(?<=[^\\w\\.]\\c\\.))(\\c\\.)+");
   ByteRegex other = new ByteRegex("[\\.]");
   ByteRegex combi = ByteRegex.combine(number, abbrev, other);

   public ConvertDotsQuery(Extractor extractor, String process) {
      super(extractor, process);
   }

   public void process(Entity entity, Entity.Section pos, String attribute) {
      ArrayList<ByteSearchPosition> positions = combi.findAllPos(entity.content, pos.open, pos.close);
      for (ByteSearchPosition p : positions) {
         switch (p.pattern) {
            case 0: // number
                     break;
            case 1: // abbreviation or initials
               for (int i = p.start; i < p.end - 1; i ++)
                  if (entity.content[i] == '.')
                     entity.content[i] = 0;
               //for (int i = p.start - 1; i < p.end - 1; i += 2)
               //   entity.content[i] &= (255 - 32);
               entity.content[p.end-1] = 32;
               break;
            case 2: // other . - +
               entity.content[p.start] = 32;
                  }
               }
   }
}
