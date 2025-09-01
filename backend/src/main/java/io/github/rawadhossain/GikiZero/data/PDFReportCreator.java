import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

class UserProfile {
    String username;
    String email;
    String age;
    String country;
    int streakCount;
    int totalPoints;
    List<SubmissionRecord> submissionRecords;
    List<SustainabilityTip> sustainabilityTips;
}

class SubmissionRecord {
    double emissionScore;
    double transportScore;
    double energyScore;
    double waterUsageScore;
    double dietScore;
    double wasteScore;
    double shoppingScore;
    double electronicUsageScore;
    double travelScore;
    double applianceScore;
    String environmentalCategory;
}

class SustainabilityTip {
    String title;
    String category;
    String impact;
    String details;
    String reasoning;
}

public class PDFReportCreator {

    public static byte[] createPDFReport(UserProfile userProfile, String reportType) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument, PageSize.A4);

        float pageWidth = pdfDocument.getDefaultPageSize().getWidth();
        float pageHeight = pdfDocument.getDefaultPageSize().getHeight();

        // Fonts
        PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        PdfFont regularFont = PdfFontFactory.createFont(StandardFonts.HELVETICA);

        // Colors
        Color mainColor = new DeviceRgb(34, 139, 34); // Green
        Color secondaryColor = new DeviceRgb(100, 100, 100); // Grey
        Color highlightColor = new DeviceRgb(0, 123, 255); // Blue

        // Header
        Paragraph header = new Paragraph("🌍 EcoTrack")
                .setFont(boldFont)
                .setFontSize(32)
                .setFontColor(Color.WHITE)
                .setMarginTop(10)
                .setMarginBottom(0);
        document.add(header);

        Paragraph subHeader = new Paragraph("Environmental Impact Report - " + capitalize(reportType))
                .setFont(regularFont)
                .setFontSize(14)
                .setFontColor(Color.WHITE)
                .setMarginTop(0)
                .setMarginBottom(20);
        document.add(subHeader);

        // Date metadata
        String currentDate = new SimpleDateFormat("MMMM dd, yyyy, HH:mm").format(new Date());
        document.add(new Paragraph("Generated on: " + currentDate)
                .setFont(regularFont)
                .setFontColor(secondaryColor)
                .setFontSize(12)
        );

        document.add(new Paragraph("\n"));

        // User Information Section
        document.add(new Paragraph("👤 User Information")
                .setFont(boldFont)
                .setFontSize(18)
                .setFontColor(mainColor)
        );

        document.add(new Paragraph("Username: " + (userProfile.username != null ? userProfile.username : "Not provided")).setFont(regularFont).setFontSize(11));
        document.add(new Paragraph("Email: " + userProfile.email).setFont(regularFont).setFontSize(11));
        document.add(new Paragraph("Age: " + (userProfile.age != null ? userProfile.age : "Not provided")).setFont(regularFont).setFontSize(11));
        document.add(new Paragraph("Country: " + (userProfile.country != null ? userProfile.country : "Not provided")).setFont(regularFont).setFontSize(11));

        document.add(new Paragraph("\n"));

        // Performance Summary
        if (userProfile.submissionRecords != null && !userProfile.submissionRecords.isEmpty()) {
            SubmissionRecord latestRecord = userProfile.submissionRecords.get(0);
            double averageScore = userProfile.submissionRecords.stream().mapToDouble(record -> record.emissionScore).average().orElse(0);

            document.add(new Paragraph("📊 Performance Summary")
                    .setFont(boldFont)
                    .setFontSize(18)
                    .setFontColor(highlightColor)
            );

            document.add(new Paragraph("Latest Emission Score: " + String.format("%.1f kg CO₂", latestRecord.emissionScore)).setFont(regularFont).setFontSize(12));
            document.add(new Paragraph("Average Score: " + String.format("%.1f kg CO₂", averageScore)).setFont(regularFont).setFontSize(12));
            document.add(new Paragraph("Environmental Impact Category: " + latestRecord.environmentalCategory).setFont(regularFont).setFontSize(12));
            document.add(new Paragraph("Total Submissions: " + userProfile.submissionRecords.size()).setFont(regularFont).setFontSize(12));
            document.add(new Paragraph("Current Streak: " + userProfile.streakCount + " days").setFont(regularFont).setFontSize(12));
            document.add(new Paragraph("Total Points: " + userProfile.totalPoints).setFont(regularFont).setFontSize(12));

            document.add(new Paragraph("\n"));
            document.add(new Paragraph("🏷️ Category Breakdown").setFont(boldFont).setFontSize(18).setFontColor(mainColor));
            document.add(new Paragraph("\n"));

            // Example: Showing Transport Category
            document.add(new Paragraph("🚗 Transportation Score: " + latestRecord.transportScore + " kg CO₂").setFont(regularFont).setFontSize(11));
        }

        // Sustainability Tips Section
        if (userProfile.sustainabilityTips != null && !userProfile.sustainabilityTips.isEmpty()) {
            document.add(new Paragraph("\n💡 Sustainability Tips").setFont(boldFont).setFontSize(18).setFontColor(mainColor));

            int count = 1;
            for (SustainabilityTip tip : userProfile.sustainabilityTips) {
                document.add(new Paragraph(count + ". " + tip.title).setFont(boldFont).setFontSize(13));
                document.add(new Paragraph(tip.category + " • " + tip.impact + " Impact").setFont(regularFont).setFontSize(9).setFontColor(secondaryColor));
                document.add(new Paragraph(tip.details).setFont(regularFont).setFontSize(10));
                document.add(new Paragraph("💭 " + tip.reasoning).setFont(regularFont).setFontSize(10).setFontColor(secondaryColor));
                document.add(new Paragraph("\n"));
                count++;
            }
        }

        // Footer with Page Number
        pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, new IEventHandler() {
            @Override
            public void handleEvent(Event event) {
                int pageNumber = pdfDocument.getPageNumber(((PdfDocumentEvent) event).getPage());
                float y = pageHeight - 20;
                Document doc = new Document(pdfDocument);
                Paragraph footer = new Paragraph("Generated by EcoTrack - Your Environmental Impact Assistant • Page " + pageNumber)
                        .setFont(regularFont)
                        .setFontSize(9)
                        .setFontColor(secondaryColor)
                        .setTextAlignment(TextAlignment.CENTER);
                doc.showTextAligned(footer, pageWidth / 2, y, pageNumber, TextAlignment.CENTER, 0);
            }
        });

        document.close();
        return outputStream.toByteArray();
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
