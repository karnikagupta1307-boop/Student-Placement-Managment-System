package com.example.placement;

public class Certification {
    public String name, organization, issueDate, credentialId, verificationUrl, fileName;
    public String fileUri;
    public Certification(String name, String organization, String issueDate, String credentialId, String verificationUrl) {
        this.name=name; this.organization=organization; this.issueDate=issueDate; this.credentialId=credentialId; this.verificationUrl=verificationUrl;
    }
}
