package com.exercise;

import com.exercise.domain.entity.Person;
import net.sf.jsqlparser.JSQLParserException;
import org.joda.time.DateTime;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;

/**
 * @author jianglong.li@hand-china.com
 * @date 2021-01-18 17:16
 **/
public class ExerciseTest {
    public static String sql2="SELECT\n" +
            "H.receipt_header_id,\n" +
            "H.receipt_number,\n" +
            "H.order_number,\n" +
            "H.receive_type,\n" +
            "H.document_type,\n" +
            "H.source_system,\n" +
            "H.accounting_date,\n" +
            "H.company_code,\n" +
            "H.merchant_number,\n" +
            "H.bank_account_id,\n" +
            "(SELECT P.soa_acc_code FROM hscs_hspm.hspm_bank_accounts P WHERE P.account_id = H.bank_account_id )ACC_CODE,\n" +
            "(SELECT L.contract_num FROM hscs_hsar.hsar_receipt_lines L WHERE H.receipt_header_id = L.receipt_header_id LIMIT 1)CONTRACT_NUM,\n" +
            "(SELECT L.receive_type FROM hscs_hsar.hsar_receipt_lines L WHERE H.receipt_header_id = L.receipt_header_id AND (L.s_is_adjust = 'Y' OR L.s_is_adjust IS NULL) LIMIT 1)L_RECEIVE_TYPE,\n" +
            "(SELECT L.is_pay_for FROM hscs_hsar.hsar_receipt_lines L WHERE H.receipt_header_id = L.receipt_header_id LIMIT 1)L_PAID,\n" +
            "(SELECT L.capital_customer_num FROM hscs_hsar.hsar_receipt_lines L WHERE H.receipt_header_id = L.receipt_header_id LIMIT 1)L_CAPITAL_NUM,\n" +
            "(SELECT        C.autarky_status FROM\n" +
            "                        hscs_hsar.hsar_receipt_lines L,\n" +
            "                        hscs_hsar.hsar_contract_headers C\n" +
            "                WHERE        H.receipt_header_id = L.receipt_header_id\n" +
            "                AND L.contract_num = C.contract_number\n" +
            "                AND (L.s_is_adjust = 'Y' OR L.s_is_adjust IS NULL)\n" +
            "                LIMIT 1\n" +
            "        ) L_AUTARKY,\n" +
            "H.party_site_number,\n" +
            "H.receipt_date,\n" +
            "H.receipt_amount,\n" +
            "H.receipt_baseamount,\n" +
            "H.wapply_amount,\n" +
            "H.poundage_fee,\n" +
            "H.is_collection,\n" +
            "H.is_pay_for,\n" +
            "H.prepay_status,\n" +
            "H.payer_number,\n" +
            "(\n" +
            "                SELECT\n" +
            "                        M.merchant_name\n" +
            "                FROM\n" +
            "                        hscs_hspm.hspm_party_merchant M\n" +
            "                WHERE\n" +
            "                        H.payer_number = M.merchant_number) PAYER_NAME,\n" +
            "H.payment_number,\n" +
            "H.currency_code,\n" +
            "H.receipt_method,\n" +
            "H.document_status,\n" +
            "H.created_by\n" +
            "FROM hsar_receipt_headers H";

    public static void main(String[] args) throws JSQLParserException {
        Class<Person> clazz = Person.class;
        Field[] fields = clazz.getDeclaredFields();
        Arrays.asList(fields).stream().forEach(e->{
            if (e.getType().isAssignableFrom(Date.class)||e.getType().isAssignableFrom(DateTime.class)){
                System.out.println(e.getType().getTypeName());
            }
        });

    }
}
