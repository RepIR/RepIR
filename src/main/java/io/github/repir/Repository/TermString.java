package io.github.repir.Repository;

import io.github.repir.Repository.TermString.File;
import io.github.repir.Retriever.Document;
import io.github.repir.tools.Content.Datafile;
import io.github.repir.tools.Content.Datafile.Status;
import io.github.repir.tools.Content.RecordJumpArray;
import io.github.repir.tools.Content.StructuredDataStream;
import io.github.repir.tools.Lib.Log;
import java.io.EOFException;

public class TermString extends VocabularyToString<File> {

   public static Log log = new Log(TermString.class);
   private boolean loaded = false;
   private String[] cache = new String[0];

   protected TermString(Repository repository) {
      super(repository);
   }

   public String readValue(int id) {
      if (id < cache.length) {
         return cache[id];
      }
      if (file == null || file.getDatafile().status != Status.READ) {
         openRead();
      }
      file.read(id);
      return file.term.value;
   }

   public void loadMem(int size) {
      size = Math.min(size, repository.getVocabularySize());
      if (size != cache.length) {
         super.openRead();
         file.setBufferSize(4096 * 10000);
         file.setOffset(0);
         cache = new String[size];
         int entry = 0;
         try {
            for (entry = 0; entry < size; entry++) {
               cache[entry] = file.term.read();
            }
         } catch (EOFException ex) {
            log.info("EOF reached at term %d", entry);
            log.exception(ex, "loadMem( %d ) index %s vocfile %s", size, repository, file);
         }
         file.setBufferSize(1000);
      }
   }
   
   public void openRead() {
      loadMem(100000);
   }

   public void closeRead() {
      super.closeRead();
      cache = new String[0];
   }

   public void write(String term) {
      file.term.write(term);
   }

   @Override
   public File createFile(Datafile datafile) {
      return new File(datafile);
   }

   @Override
   public void reduceInput(int id, String term, long tf, long df) {
        write(term);
   }

   @Override
   public void startReduce(long corpustermfreq, int corpusdocumentfrequency) {
      openWrite();
   }

   @Override
   public void finishReduce() {
      closeWrite();
   }

   @Override
   public void setBufferSize(int size) {
      file.setBufferSize(size);
   }

   public class File extends RecordJumpArray {

      public StructuredDataStream.String0Field term = this.addString0("term");

      public File(Datafile df) {
         super(df);
      }
   }
}
