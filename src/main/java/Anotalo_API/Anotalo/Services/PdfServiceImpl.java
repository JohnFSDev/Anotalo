package Anotalo_API.Anotalo.Services;

import Anotalo_API.Anotalo.Models.Note;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

@Service
public class PdfServiceImpl implements PdfService {

    @Override
    public byte[] generatePdfFromNote(Note note) throws IOException {
        ByteArrayOutputStream outputStream;
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText(note.getTitle());
                contentStream.endText();
                
                // Handling content text wrapping
                String content = note.getContent();
                List<String> lines = breakTextIntoLines(content, PDType1Font.HELVETICA, 12, 400); // Adjust width as needed
                
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                float yPosition = 680; // Starting y position for content
                for (String line : lines) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(100, yPosition);
                    contentStream.showText(line);
                    contentStream.endText();
                    yPosition -= 15; // Line spacing
                }
            }
            
            outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
        }

        return outputStream.toByteArray();
    }
    
    private List<String> breakTextIntoLines(String text, PDFont font, float fontSize, float maxWidth) throws IOException {
        List<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();
        String[] words = text.split("\\s+");
        
        for (String word : words) {
            if (font.getStringWidth(currentLine + " " + word) / 1000 * fontSize > maxWidth) {
                lines.add(currentLine.toString());
                currentLine = new StringBuilder();
            }
            if (currentLine.length() > 0) {
                currentLine.append(" ");
            }
            currentLine.append(word);
        }
        
        if (currentLine.length() > 0) {
            lines.add(currentLine.toString());
        }
        
        return lines;
    }
}