package com.acmemini.ledger;

import java.util.List;

/** Books an approved payment as two ledger entries in core.ledger_entry: debit the payer, credit clearing. */
public class LedgerPosting {
    public static final String CLEARING_ACCOUNT = "CLR-001";

    public record Entry(String paymentId, String account, String side, long amountCents) {}

    public List<Entry> post(String paymentId, String payerAccount, long amountCents) {
        if (amountCents <= 0) throw new IllegalArgumentException("amount must be positive");
        return List.of(new Entry(paymentId, payerAccount, "DEBIT", amountCents),
                       new Entry(paymentId, CLEARING_ACCOUNT, "CREDIT", amountCents));
    }
}
