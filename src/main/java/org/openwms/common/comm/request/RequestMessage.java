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
package org.openwms.common.comm.request;

import java.io.Serializable;
import java.util.Date;

import org.openwms.common.comm.common.CommonMessage;
import org.openwms.common.domain.LocationPK;
import org.openwms.common.domain.values.Barcode;
import org.springframework.core.serializer.Serializer;

/**
 * A RequestMessage requests an order for a TransportUnit with id
 * <tt>Barcode</tt> on a particular location <tt>actualLocation</tt>.
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision: $
 * @since 0.1
 */
public class RequestMessage extends CommonMessage {

    private static final long serialVersionUID = 1L;
    public static final String IDENTIFIER = "REQ_";
    private final String identifier = IDENTIFIER;

    private Barcode barcode;
    private LocationPK actualLocation;
    private LocationPK targetLocation;

    /**
     * Create a new RequestMessage.
     * 
     * @param telegram
     */
    public RequestMessage() {}

    /**
     * @see org.openwms.common.comm.common.CommonMessage#getMessageIdentifier()
     */
    @Override
    public String getMessageIdentifier() {
        return identifier;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RequestMessage [identifier=" + IDENTIFIER + ", barcode=" + barcode + ", actualLocation="
                + actualLocation + ", targetLocation=" + targetLocation + ", errorCode=" + getErrorCode()
                + ", created=" + getCreated() + "]";
    }

    /**
     * A Builder.
     * 
     * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
     * @version $Revision: $
     * @since 0.1
     */
    public static class Builder {

        private final RequestMessage requestMessage;

        /**
         * Create a new RequestMessage.Builder.
         */
        public Builder() {
            this.requestMessage = new RequestMessage();
        }

        public Builder withBarcode(Barcode barcode) {
            requestMessage.barcode = barcode;
            return this;
        }

        public Builder withActualLocation(LocationPK actualLocation) {
            requestMessage.actualLocation = actualLocation;
            return this;
        }

        public Builder withTargetLocation(LocationPK targetLocation) {
            requestMessage.targetLocation = targetLocation;
            return this;
        }

        public Builder withErrorCode(String errorCode) {
            requestMessage.setErrorCode(errorCode);
            return this;
        }

        public Builder withCreateDate(Date createDate) {
            requestMessage.setCreated(createDate);
            return this;
        }

        public RequestMessage build() {
            return requestMessage;
        }
    }

    /**
     * @see org.openwms.common.comm.common.CommonMessage#serialize(org.springframework.core.serializer.Serializer)
     */
    @Override
    public String serialize(Serializer<Serializable> serializer) {
        // TODO [scherrer] Auto-generated method stub
        return null;
    }
}
