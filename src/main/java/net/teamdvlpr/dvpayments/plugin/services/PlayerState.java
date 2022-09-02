package net.teamdvlpr.dvpayments.plugin.services;

public class PlayerState {
    private boolean paid;
    public boolean error;

    public boolean hasError() {
        return error;
    }

    public void setError() {
        error = true;
    }

    public boolean isPaid() {
        if(paid) {
            // PaymentRespose
        } else {
            // PaymentRequest
        }

        return paid;
    }
}
