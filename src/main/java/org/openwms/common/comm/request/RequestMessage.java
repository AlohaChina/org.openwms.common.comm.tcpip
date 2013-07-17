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

import java.util.Date;

import org.openwms.common.comm.CommConstants;
import org.openwms.common.comm.CommonHeader;
import org.openwms.common.comm.CommonMessage;
import org.openwms.common.domain.LocationPK;
import org.openwms.common.domain.values.Barcode;

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
     * @param header
     */
    public RequestMessage(CommonHeader header) {
        super(header);
    }

    /**
     * @see org.openwms.common.comm.CommonMessage#getMessageIdentifier()
     */
    @Override
    public String getMessageIdentifier() {
        return identifier;
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
        public Builder(CommonHeader header) {
            this.requestMessage = new RequestMessage(header);
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
     * @see org.openwms.common.comm.CommonMessage#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(IDENTIFIER).append(this.barcode).append(this.actualLocation).append(this.targetLocation)
                .append(getErrorCode()).append(CommConstants.asString(super.getCreated()));
        return sb.toString();
    }

    /**
     * @see org.openwms.common.comm.CommonMessage#isWithoutReply()
     */
    @Override
    public boolean isWithoutReply() {
        return false;
    }
}
