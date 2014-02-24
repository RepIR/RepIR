package io.github.repir.Extractor.Tools;

import io.github.repir.tools.ByteRegex.ByteRegex;
import io.github.repir.tools.ByteRegex.ByteRegex.Pos;
import io.github.repir.Extractor.Entity;
import io.github.repir.Extractor.Extractor;
import io.github.repir.tools.Lib.Log;
import java.util.ArrayList;

/**
 * Marks <doctitle> </doctitle> sections.
 * <p/>
 * @author jbpvuurens
 */
public class MarkText extends SectionMarker {

   public static Log log = new Log(MarkText.class);
   public ByteRegex endmarker = new ByteRegex("</text>");

   public MarkText(Extractor extractor, String inputsection, String outputsection) {
      super(extractor, inputsection, outputsection);
   }

   @Override
   public ByteRegex getStartMarker() {
      return new ByteRegex("<text>");
   }

   @Override
   public void process(Entity entity, int sectionstart, int sectionend, ArrayList<Pos> positions) {
      for (Pos start : positions) {
            Pos end = endmarker.find(entity.content, start.end, sectionend);
            if (end.found()) {
               entity.addSectionPos(outputsection, start.start, start.end, end.start, end.end);
            }
      }
   }
}
