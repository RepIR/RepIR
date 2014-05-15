package io.github.repir.Extractor.Tools;

import io.github.repir.tools.ByteSearch.ByteRegex;
import io.github.repir.tools.ByteSearch.ByteSearchPosition;
import io.github.repir.EntityReader.Entity;
import io.github.repir.Extractor.Extractor;
import io.github.repir.tools.ByteSearch.ByteSearch;
import io.github.repir.tools.Lib.Log;
import java.util.ArrayList;

/**
 * Marks <video> </video> sections.
 * <p/>
 * @author jbpvuurens
 */
public class MarkVideo extends SectionMarker {

   public static Log log = new Log(MarkVideo.class);
   public ByteSearch endmarker = ByteSearch.create("</video").toSection(ByteSearch.create(">"));

   public MarkVideo(Extractor extractor, String inputsection, String outputsection) {
      super(extractor, inputsection, outputsection);
   }

   @Override
   public ByteRegex getStartMarker() {
      return new ByteRegex("<video");
   }

   @Override
   public void process(Entity entity, int sectionstart, int sectionend, ByteSearchPosition position) {
      int tagclose = findQuoteSafeTagEnd(entity, position.end, sectionend) + 1;
      if (tagclose > -1) {
         ByteSearchPosition end = endmarker.findPos(entity.content, position.end, sectionend);
         if (end.start > position.end) {
            entity.addSectionPos(outputsection, position.start, tagclose, end.start, end.end);
         }
      }
   }
}
