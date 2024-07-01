package Anotalo_API.Anotalo.Services;

import Anotalo_API.Anotalo.Models.Note;
import java.io.IOException;


public interface PdfService {

    byte[] generatePdfFromNote(Note note) throws IOException;

}