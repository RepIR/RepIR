package io.github.repir.Extractor.Tools;

import io.github.repir.tools.Lib.Log;
import io.github.repir.EntityReader.Entity;
import io.github.repir.EntityReader.Entity.Section;
import io.github.repir.Extractor.EntityChannel;
import io.github.repir.Extractor.Extractor;
import java.util.Map;

/**
 * Shows the current assigned attributes for debugging
 * <p/>
 * @author jeroen
 */
public class ShowAttributes extends ExtractorProcessor {

   public static Log log = new Log(ShowAttributes.class);

   public ShowAttributes(Extractor extractor, String process) {
      super(extractor, process);
   }

   @Override
   public void process(Entity entity, Section section, String attribute) {
      for (Map.Entry<String, EntityChannel> entry : entity.entrySet()) {
         log.info("%s=%s", entry.getKey(), entry.getValue().getContentStr());
      }
   }
}
