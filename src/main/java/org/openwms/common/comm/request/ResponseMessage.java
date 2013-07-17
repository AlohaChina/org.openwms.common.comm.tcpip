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

import org.openwms.common.comm.CommConstants;
import org.openwms.common.comm.CommonHeader;
import org.openwms.common.comm.CommonMessage;

/**
 * A ResponseMessage.
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision: $
 * 
 */
public class ResponseMessage extends CommonMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String IDENTIFIER = "RES_";
    private final String identifier = IDENTIFIER;

    /**
     * Create a new ResponseMessage.
     * 
     * @param header
     */
    public ResponseMessage(CommonHeader header) {
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
     * @see org.openwms.common.comm.CommonMessage#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(IDENTIFIER).append(getErrorCode()).append(CommConstants.asString(super.getCreated()));
        return CommConstants.padRight(sb.toString(), getHeader().getMessageLength());
    }

    /**
     * @see org.openwms.common.comm.CommonMessage#isWithoutReply()
     */
    @Override
    public boolean isWithoutReply() {
        return true;
    }
}
