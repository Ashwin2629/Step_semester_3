@SuppressWarnings("unused")
public class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(
            String memberId,
            String[] bookIds) {

        this.memberId = memberId;
        this.bookIds = bookIds.clone();
    }

    public String[] getBookIds() {

        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(
            int index,
            String newId) {

        String[] correctedBookIds =
                bookIds.clone();

        correctedBookIds[index] = newId;

        return new LoanReceipt(
                memberId,
                correctedBookIds
        );
    }
}