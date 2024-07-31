package com.greenmark.common.dto.account.decorator;

import com.greenmark.common.GmConstantsAccount;
import com.greenmark.common.dto.account.AccountDto;
import com.greenmark.utils.UTDatetime;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: AccountDtoDecorator</p>
 * <p>Description: This decorator class contains convenience methods for its AccountDto base class.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class AccountDtoDecorator extends AccountDto implements Serializable {
    public static final String CLASSNAME = "AccountDtoDecorator";
    private static final long serialVersionUID = 1L;

    public AccountDtoDecorator() {
        super();
    }

//	public AccountDtoDecorator(String xmldata) {
//		super(xmldata, trace);
//	}

    public AccountDtoDecorator(AccountDtoDecorator inAccount) {
        this();

        try {
            if (inAccount != null)
                BeanUtils.copyProperties(this, inAccount);
        } catch (InvocationTargetException ite) {
            System.out.println("ERROR creating " + CLASSNAME + ", InvocationTargetException, message: " + ite.getMessage());
        } catch (IllegalAccessException iae) {
            System.out.println("ERROR creating " + CLASSNAME + ", IllegalAccessException, message: " + iae.getMessage());
        }
    }

    public String dbSummary() {
        String stb = " > > " + CLASSNAME + ":" +
                "accountId [" + id + "] " +
                "name [" + name + "] " +
                "active [" + active + "] ";
        return stb;
    }

    public String toString() {
        String outString = "ACCOUNT:   " +
                id +
                "   " +
                name +
                "   " +
                active +
                "   ";

        return outString;
    }

    public String getRealtimeStartTimeString() {
        if (realtimeAccountStartTime == null)
            return "";
        return UTDatetime.toDbString(realtimeAccountStartTime);
    }

    public boolean isAccountTypeRegT() {
        return this.accountType == GmConstantsAccount.ACCOUNT_TYPE_REG_T_MARGIN;
    }
}
