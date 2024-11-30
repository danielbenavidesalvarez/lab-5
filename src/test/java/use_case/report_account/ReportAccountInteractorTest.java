package use_case.report_account;

import data_access.InMemoryReportAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportAccountInteractorTest {
    private ReportAccountInteractor interactor;
    private InMemoryReportAccountRepository repository;
    private MockReportAccountOutputBoundary outputBoundary;

    @BeforeEach
    void setUp() {
        repository = new InMemoryReportAccountRepository();
        outputBoundary = new MockReportAccountOutputBoundary();
        interactor = new ReportAccountInteractor(outputBoundary, repository);
    }

    @Test
    void testValidReport() {
        ReportAccountInputData inputData = new ReportAccountInputData(
                "user123",
                "Bullying/Harassment",
                "This user is sending offensive messages."
        );

        interactor.reportAccount(inputData);

        // Verify the success message
        assertTrue(outputBoundary.isSuccess());
        assertEquals("Report submitted successfully.", outputBoundary.getMessage());

        // Verify the report is saved in the repository
        assertEquals(1, repository.getAllReports().size());
        InMemoryReportAccountRepository.Report savedReport = repository.getAllReports().get(0);
        assertEquals("user123", savedReport.getReportedUserId());
        assertEquals("Bullying/Harassment", savedReport.getIssueType());
        assertEquals("This user is sending offensive messages.", savedReport.getDescription());
    }

    @Test
    void testInvalidInput_missingUserId() {
        ReportAccountInputData inputData = new ReportAccountInputData(
                "",
                "Bullying/Harassment",
                "This user is sending offensive messages."
        );

        interactor.reportAccount(inputData);

        // Verify the error message
        assertFalse(outputBoundary.isSuccess());
        assertEquals("User ID cannot be empty.", outputBoundary.getMessage());

        // Verify no report is saved
        assertEquals(0, repository.getAllReports().size());
    }

    @Test
    void testInvalidInput_missingIssueType() {
        ReportAccountInputData inputData = new ReportAccountInputData(
                "user123",
                "",
                "This user is sending offensive messages."
        );

        interactor.reportAccount(inputData);

        // Verify the error message
        assertFalse(outputBoundary.isSuccess());
        assertEquals("Issue type cannot be empty.", outputBoundary.getMessage());

        // Verify no report is saved
        assertEquals(0, repository.getAllReports().size());
    }

    @Test
    void testInvalidInput_missingDescription() {
        ReportAccountInputData inputData = new ReportAccountInputData(
                "user123",
                "Bullying/Harassment",
                ""
        );

        interactor.reportAccount(inputData);

        // Verify the error message
        assertFalse(outputBoundary.isSuccess());
        assertEquals("Description cannot be empty.", outputBoundary.getMessage());

        // Verify no report is saved
        assertEquals(0, repository.getAllReports().size());
    }

    // Mock Output Boundary for testing
    private static class MockReportAccountOutputBoundary implements ReportAccountOutputBoundary {
        private boolean success;
        private String message;

        @Override
        public void presentReportResult(ReportAccountOutputData outputData) {
            this.success = outputData.isSuccess();
            this.message = outputData.getMessage();
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}
