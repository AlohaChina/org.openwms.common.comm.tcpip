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
package org.openwms.common.comm.tcpip;

import java.io.Serializable;

/**
 * A CommonHeader.
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision: $
 * @since 0.1
 */
public class CommonHeader implements Serializable {

    private static final long serialVersionUID = 1L;
    private String sync;
    private short telegramLength;
    private String sender;
    private String receiver;
    private short sequenceNo;

    /**
     * Create a new CommonHeader.
     */
    public CommonHeader() {}

    /**
     * Create a new CommonHeader.
     * 
     * @param sync
     * @param telegramLength
     * @param sender
     * @param receiver
     * @param sequenceNo
     */
    public CommonHeader(String sync, short telegramLength, String sender, String receiver, short sequenceNo) {
        super();
        this.sync = sync;
        this.telegramLength = telegramLength;
        this.sender = sender;
        this.receiver = receiver;
        this.sequenceNo = sequenceNo;
    }

    /**
     * Return the number of characters the message header allocates.
     * 
     * @return 23
     */
    public static final short getHeaderLength() {
        return (short) 23;
    }

    /**
     * Get the sync.
     * 
     * @return the sync.
     */
    public String getSync() {
        return sync;
    }

    /**
     * Set the sync.
     * 
     * @param sync
     *            The sync to set.
     */
    public void setSync(String sync) {
        this.sync = sync;
    }

    /**
     * Get the telegramLength.
     * 
     * @return the telegramLength.
     */
    public short getTelegramLength() {
        return telegramLength;
    }

    /**
     * Set the telegramLength.
     * 
     * @param telegramLength
     *            The telegramLength to set.
     */
    public void setTelegramLength(short telegramLength) {
        this.telegramLength = telegramLength;
    }

    /**
     * Get the sender.
     * 
     * @return the sender.
     */
    public String getSender() {
        return sender;
    }

    /**
     * Set the sender.
     * 
     * @param sender
     *            The sender to set.
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * Get the receiver.
     * 
     * @return the receiver.
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * Set the receiver.
     * 
     * @param receiver
     *            The receiver to set.
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * Get the sequenceNo.
     * 
     * @return the sequenceNo.
     */
    public short getSequenceNo() {
        return sequenceNo;
    }

    /**
     * Set the sequenceNo.
     * 
     * @param sequenceNo
     *            The sequenceNo to set.
     */
    public void setSequenceNo(short sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((receiver == null) ? 0 : receiver.hashCode());
        result = prime * result + ((sender == null) ? 0 : sender.hashCode());
        result = prime * result + sequenceNo;
        result = prime * result + ((sync == null) ? 0 : sync.hashCode());
        result = prime * result + telegramLength;
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CommonHeader other = (CommonHeader) obj;
        if (receiver == null) {
            if (other.receiver != null) {
                return false;
            }
        } else if (!receiver.equals(other.receiver)) {
            return false;
        }
        if (sender == null) {
            if (other.sender != null) {
                return false;
            }
        } else if (!sender.equals(other.sender)) {
            return false;
        }
        if (sequenceNo != other.sequenceNo) {
            return false;
        }
        if (sync == null) {
            if (other.sync != null) {
                return false;
            }
        } else if (!sync.equals(other.sync)) {
            return false;
        }
        if (telegramLength != other.telegramLength) {
            return false;
        }
        return true;
    }
}
