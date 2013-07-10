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
package org.openwms.common.comm.tcpip.common;

import java.text.ParseException;
import java.util.Date;

import org.openwms.common.comm.tcpip.CommonHeader;
import org.openwms.common.comm.tcpip.CommonMessage;

/**
 * A ErrorTelegramMessage.
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision: $
 * @since 0.1
 */
public class ErrorTelegramMessage extends CommonMessage {

    private static final long serialVersionUID = 1L;
    private final String telegramIdentifier = IDENTIFIER;
    public static final String IDENTIFIER = "ERR_";

    /**
     * Create a new ErrorTelegramMessage.
     * 
     * @param header
     */
    public ErrorTelegramMessage(CommonHeader header) {
        super(header);
    }

    /**
     * Create a new ErrorTelegramMessage.
     */
    public ErrorTelegramMessage() {}

    /**
     * @see org.openwms.common.comm.tcpip.CommonMessage#getTelegramIdentifier()
     */
    @Override
    public String getTelegramIdentifier() {
        return telegramIdentifier;
    }

    /**
     * A Builder.
     * 
     * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
     * @version $Revision: $
     * @since 0.1
     */
    public static class Builder {

        private final ErrorTelegramMessage message;

        /**
         * Create a new ErrorTelegramMessage.Builder.
         */
        public Builder() {
            this.message = new ErrorTelegramMessage();
        }

        /**
         * Add an error code to the message.
         * 
         * @param errorCode
         * @return The builder
         */
        public Builder withErrorCode(String errorCode) {
            message.setErrorCode(errorCode);
            return this;
        }

        /**
         * Add a creation Date to the Message.
         * 
         * @param createDate
         *            The date of creation
         * @return The builder
         * @throws ParseException
         *             in case of an invalid date string
         */
        public Builder withCreateDate(String createDate) throws ParseException {
            message.setCreated(asDate(createDate));
            return this;
        }

        /**
         * Add a new instance of Date to the Message.
         * 
         * @return The builder
         */
        public Builder withCreateDate() {
            message.setCreated(new Date());
            return this;
        }

        /**
         * Build and return the Message.
         * 
         * @return The Message
         */
        public ErrorTelegramMessage build() {
            return message;
        }
    }
}
