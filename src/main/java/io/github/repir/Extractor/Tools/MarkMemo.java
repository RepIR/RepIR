package io.github.repir.Extractor.Tools;

import io.github.repir.tools.ByteSearch.ByteRegex;
import io.github.repir.tools.ByteSearch.ByteSearchPosition;
import io.github.repir.Extractor.Entity;
import io.github.repir.Extractor.Extractor;
import io.github.repir.tools.ByteSearch.ByteSearch;
import io.github.repir.tools.Lib.Log;
import java.util.ArrayList;

/**
 * Marks <memo> </memo> sections.
 * <p/>
 * @author jbpvuurens
 */
public class MarkMemo extends SectionMarker {

   public static Log log = new Log(MarkMemo.class);
   public ByteSearch endmarker = ByteSearch.create("</memo>");

   public MarkMemo(Extractor extractor, String inputsection, String outputsection) {
      super(extractor, inputsection, outputsection);
   }

   @Override
   public ByteRegex getStartMarker() {
      return new ByteRegex("<memo>");
   }

   @Override
   public void process(Entity entity, int sectionstart, int sectionend, ByteSearchPosition position) {
      ByteSearchPosition end = endmarker.findPos(entity.content, position.end, sectionend);
      if (end.found() && end.start > position.end) {
         entity.addSectionPos(outputsection, position.start, position.end, end.start, end.end);
      }
   }
}
