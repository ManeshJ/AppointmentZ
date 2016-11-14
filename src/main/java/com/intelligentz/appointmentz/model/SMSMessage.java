package com.intelligentz.appointmentz.model;

/**
 * Created by lakshan on 11/12/16.
 */
public class SMSMessage {
    String message;
    String messageId;
    String destinationAddress;
    String sourceAddress;
    String Operator;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getOperator() {
        return Operator;
    }

    public void setOperator(String operator) {
        Operator = operator;
    }
}
