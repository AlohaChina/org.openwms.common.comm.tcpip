/*
 * openwms.org, the Open Warehouse Management System.
 *
 * This file is part of openwms.org.
 *
 * openwms.org is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * openwms.org is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software. If not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.openwms.common.comm;

import java.io.Serializable;
import java.util.Date;

import org.springframework.core.serializer.Serializer;

/**
 * A CommonMessage is the abstract superclass of all messages sent to subsystems
 * like PLC or ERP. A CommonMessage has always a message header and a body.
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision: $
 * @since 0.1
 */
public abstract class CommonMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    private CommonHeader header;
    private String errorCode;
    private Date created;

    private static final short ERROR_CODE_LENGTH = 8;
    private static final short DATE_LENGTH = 14;

    /**
     * Create a new CommonMessage.
     * 
     * @param herader
     *            The message header
     */
    public CommonMessage(CommonHeader header) {
        this.header = header;
    }

    /**
     * Create a new CommonMessage.
     */
    public CommonMessage() {}

    /**
     * FIXME [scherrer] Comment this
     * 
     * @param telegram
     * @return
     */
    // TODO [scherrer] : remove this it is specific to telegrams.
    public CommonHeader createHeader(String telegram) {
        return new CommonHeader(telegram);
    }

    /**
     * Return the length of a date field used in telegram messages.
     * 
     * @return Length of a date field
     */
    public static final short getDateLength() {
        return DATE_LENGTH;
    }

    /**
     * Return the length of an errorCode field used in telegram messages.
     * 
     * @return Length of an errorCode
     */
    public static final short getErrorCodeLength() {
        return ERROR_CODE_LENGTH;
    }

    /**
     * Subclasses have to return an unique, case-sensitive message identifier.
     * 
     * @return The message TYPE field (see OSIP specification)
     */
    public abstract String getMessageIdentifier();

    // TODO [scherrer] : remove this it is specific to telegrams.
    public abstract String serialize(Serializer<Serializable> serializer);

    /**
     * Does this type of message needs to be replied to?
     * 
     * @return <code>true</code> no reply needed, otherwise <code>false</code>
     */
    public abstract boolean isWithoutReply();

    /**
     * Get the header.
     * 
     * @return header
     */
    public CommonHeader getHeader() {
        return header;
    }

    /**
     * A CommonMessage is able to define the type of message from the telegram
     * String. Currently the type identifier starts directly after the header
     * and has a length of 4 characters.
     * 
     * @param telegram
     *            The telegram String to resolve the type for
     * @return The telegram type as case-insensitive String
     */
    // TODO [scherrer] : remove this it is specific to telegrams.
    public static final String getTelegramType(String telegram) {
        short headerLength = CommonHeader.getHeaderLength();
        return telegram.substring(headerLength, headerLength + 4);
    }

    /**
     * Get the errorCode.
     * 
     * @return the errorCode.
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Set the errorCode.
     * 
     * @param errorCode
     *            The errorCode to set.
     */
    protected void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Get the created.
     * 
     * @return the created.
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Set the created.
     * 
     * @param created
     *            The created to set.
     */
    protected void setCreated(Date created) {
        this.created = created;
    }
}
