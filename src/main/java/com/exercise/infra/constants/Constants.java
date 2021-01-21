package com.hscs.hsae.infra.constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;


/**
 * 常量类
 * @author 张志岑 ZHICEN.ZHANG@HAND-CHINA.COM
 * @date 2020/7/13 11:25
 */
public class Constants {

    private static Logger logger = LoggerFactory.getLogger(com.hscs.hsae.infra.constants.Constants.class);

    /**
     * 数据库类型
     */
    public static String dbType;

    static {
        try {
            Properties properties = PropertiesLoaderUtils.loadAllProperties("config.properties");
            dbType = properties.getProperty("db.type");
        } catch (IOException e) {
            logger.info(e.getMessage());
            dbType = "mysql";
        }
    }

    /**
     * 映射缓存默认分隔符
     */
    public static final String CACHE_SPLIT = "θ";


    /**
     * 导出模板类型
     */
    public static final class ExportType{

        /**
         * EXCEL类型
         */
        public static final String EXCEL = "EXCEL";
    }

    public static final class ExcelConstants{

        public static final String XLS = "XLS";
        public static final String XLSX = "XLSX";
        public static final String XLS_LOWERCASE = "xls";
        public static final String POINT_XLS_LOWERCASE = ".xls";
    }

    public enum ImportState{
        /**
         * 新建
         */
        NEW,
        /**
         * 重复
         */
        REPEAT,
        /**
         * 导入错误
         */
        ERROR,
        /**
         * 导入成功
         */
        SUCCESS
    }

    /**
     * 常用标点符号
     */
    public static final class  Punctuation {

        /**
         * 逗号
         */
        public static final String COMMA = ",";

        /**
         * 星号符号
         */
        public static final String STRING_STAR = "*";

        /**
         *  换行
         */
        public static final  String ENTER_STR = "\n";

        /**
         *  退格
         */
        public static final  String TAB_STR = "\t";

        /**
         *  空格
         */
        public static final  String SPACE_STR = " ";

        /**
         * 空白
         */
        public static final String STRING_BLANK = "";

        /**
         * 点
         */
        public static final String POINT = ".";

        /**
         * matching
         */
        public static final String MATCH = ".*";

    }

    public static final class FeedAccountStatus{

        public static final String ACCOUNTED_SUCCESS = "ACCOUNTED_SUCCESS";

        public static final String ACCOUNTED_ERROR = "ACCOUNTED_ERROR";

        public static final String UN_ACCOUNTED = "UN_ACCOUNTED";

    }

    /**
     * 创建账务方向
     */
    public static final class  CreateAccountDirection {

        /**
         * 冲销程序
         */
        public static final String REV_RUN = "REV_RUN";

        /**
         * 正向创建账务程序
         */
        public final static String NEW_RUN = "NEW_RUN";
    }

    /**
     * 映射行类型
     */
    public static final class  MappingColumnType{

        public static final String AE_COLUMN_TYPE_SOURCE = "SOURCE";

        public static final String AE_COLUMN_TYPE_TARGET = "TARGET";

        public static final String AE_COLUMN_TYPE_CONSTANT = "CONSTANTS";
    }

    /**
     * 字段属性
     */
    public static final class FieldAttribute{

        public final static String DR_AMOUNT ="DR_AMOUNT";

        public final static String CR_AMOUNT ="CR_AMOUNT";

        public final static String DR_BASE_AMOUNT ="DR_BASEAMOUNT";

        public final static String CR_BASE_AMOUNT ="CR_BASEAMOUNT";
    }

    /**
     * 账务推送时最大一次推送多少数据
     */
    public static final class DeliveryAmount{

        /**
         * 默认批次大小
         */
        public final static int DEFAULT_BATCH_COUNT_1000 = 1000;

        /**
         * 默认重试次数
         */
        public final static int DEFAULT_TRY_COUNT_3 = 3;
    }

    /**
     * 生成规则取值方式
     */
    public static final class ValueFrom{

        /**
         * 常数
         */
        public static final String CONSTANT="CONSTANT";

        /**
         * 公式
         */
        public static final String FORMULA = "FORMULA";

        /**
         * 编码规则
         */
        public static final String SEQUENCE = "SEQUENCE";

        /**
         * 源数据
         */
        public static final String SOURCE = "SOURCE";

        /**
         * 映射数据
         */
        public static final String TARGET = "TARGET";
    }

    /**
     * 入账状态常量
     */
    public static final class AccountingStatus {
        //----------正向-------------
// 新建
        public static final String NEW = "NEW";
        // 前置校验失败(期间校验)
        public static final String PRE_VERIFIED_ERROR = "PRE_VERIFIED_ERROR";
        // 校验通过
        public static final String VERIFIED = "VERIFIED";
        // 校验失败
        public static final String VERIFIED_ERROR = "VERIFIED_ERROR";
        // 生成中
        public static final String GENERATING = "GENERATING";
        // 生成成功
        public static final String GENERATED = "GENERATED";
        // 生成失败
        public static final String GENERATED_ERROR = "GENERATED_ERROR";
        // 入账中
        public static final String ACCOUNTING = "ACCOUNTING";
        // 入账成功
        public static final String ACCOUNTED = "ACCOUNTED";
        // 入账异常
        public static final String ACCOUNTED_ABNORMAL = "ACCOUNTED_ABNORMAL";
        // 入账失败
        public static final String ACCOUNTED_ERROR = "ACCOUNTED_ERROR";

        //----------逆向-------------
// 逆向前置校验失败
        public static final String REV_PRE_VERIFIED_ERROR = "REV_PRE_VERIFIED_ERROR";
        // 逆向校验成功
        public static final String REV_VERIFIED = "REV_VERIFIED";
        // 逆向校验失败
        public static final String REV_VERIFIED_ERROR = "REV_VERIFIED_ERROR";
        // 逆向生成成功
        public static final String REV_GENERATED = "REV_GENERATED";
        // 逆向生成失败
        public static final String REV_GENERATED_ERROR = "REV_GENERATED_ERROR";
        // 逆向入账中
        public static final String REV_ACCOUNTING = "REV_ACCOUNTING";
        // 逆向已入账
        public static final String REV_ACCOUNTED = "REV_ACCOUNTED";
        // 逆向入账异常
        public static final String REV_ACCOUNTED_ABNORMAL = "REV_ACCOUNTED_ABNORMAL";
        // 逆向入账失败
        public static final String REV_ACCOUNTED_ERROR = "REV_ACCOUNTED_ERROR";
    }

    /**
     * 汇总方法常量
     */
    public static final class GroupingMethod{

        /**
         * 分组字段
         */
        public static final String GROUPING = "GROUPING";

        /**
         * 汇总字段
         */
        public static final String ACCUMULATE= "ACCUMULATE";
    }


    /**
     * 事件定义类型
     */
    public static final class ColumnType{
        // 源数据列
        public static final String SOURCE = "SOURCE";
        // 映射数据列
        public static final String TARGET = "TARGET";
    }

    /**
     * 数据库字段类型
     */
    public static final class DbValueType{
        /**
         * dateTime类型
         */
        public static final String DATETIME = "DATETIME";
        /**
         * DATE类型
         */
        public static final String DATE = "DATE";
        /**
         * BigInt类型
         */
        public static final String BIGINT = "BIGINT";
        /**
         * 小数类型
         */
        public static final String DECIMAL = "DECIMAL";
        /**
         * 字符类型
         */
        public static final String VARCHAR = "VARCHAR";
        /**
         * longText类型
         */
        public static final String LONGTEXT = "LONGTEXT";
    }

    /**
     * 账务推送行表
     */
    public static final class PushValueFrom{

        /**
         * 常数
         */
        public static final String CONSTANT = "CONSTANT";

        /**
         * 入账日期
         */
        public static final String ACCOUNT_DATE = "ACCOUNT_DATE";

        /**
         * 入账字段
         */
        public static final String ACCOUNT_FILED = "ACCOUNT_FILED";

        /**
         * 汇总表字段
         */
        public static final String ACCOUNT_SUMMARY_FILED = "ACCOUNT_SUMMARY";
    }


    /**
     * 通用字段类型
     */
    public static final class ValueType{

        /**
         * 日期
         */
        public static final String DATE ="DATE";
        /**
         * 日期类型
         */
        public static final String DATETIME = "DATETIME";
        /**
         * 数值
         */
        public static final String NUMBER ="NUMBER";
        /**
         * 字符
         */
        public static final String VARCHAR ="VARCHAR";

    }

    /**
     * 入账基准表
     */
    public static final class BasicTable{

        /**
         * 接口头表
         */
        public static final String 	HSAE_TFR_INTERFACE = "HSAE_TFR_INTERFACE";
    }

    /**
     * 日期格式掩码
     */
    public static final class MaskCode{
        // yyyy
        public static final String YYYY="yyyy";

        // yyyy-MM
        public static final String YYYYMM="yyyy-MM";

        // yyyy-MM-dd
        public static final String YYYYMMDD="yyyy-MM-dd";

        // yyyy-MM-dd HH:mm:ss
        public static final String YYYYMMDD_HHMMSS="yyyy-MM-dd HH:mm:ss";

        // yyyy/MM/dd
        public static final String SPLASH_YYYYMMDD="yyyy/MM/dd";

        //yyyy/MM/dd HH:mm:ss
        public static final String SPLASH_YYYYMMDD_HHMMSS="yyyy/MM/dd HH:mm:ss";

        //yyyyMMddHHmmss
        public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    }

    /**
     * 回滚范围
     */
    public static final class RollBackRange{

        //全部错误回滚
        public static final String ALL_ERROR_ROLLBACK = "ALL_ERROR_ROLLBACK";

        public static final String ACCOUNTED_ABNORMAL = "ACCOUNTED_ABNORMAL";
    }

    /**
     * 传送状态
     */
    public static final class DeliveryStatus{

        /**
         * 已删除
         */
        public static final String DELIVERY_STATUS_D = "D";
        /**
         * 传送失败
         */
        public static final String DELIVERY_STATUS_E = "E";
        /**
         * 未传送
         */
        public static final String DELIVERY_STATUS_N = "N";
        /**
         * 传送成功
         */
        public static final String DELIVERY_STATUS_S = "S";
        /**
         * 传送中
         */
        public static final String DELIVERY_STATUS_P = "P";
    }





    public static final String MINUS ="MINUS";


    /**
     * 表
     */
    public static class TableName{

        /**
         * 会计引擎接口表
         */
        public static final String INTERFACE_TABLE = "HSAE_TFR_INTERFACE";

        /**
         * 会计引擎接口行表
         */
        public static final String INTERFACE_LINE_TABLE = "HSAE_TFR_LINE_INTERFACE";

        /**
         * 账务明细表
         */
        public static final String TFR_DTL_ACCOUNTS = "HSAE_TFR_DTL_ACCOUNTS";

        /**
         * 账务明细表
         */
        public static final String TFR_SUM_ACCOUNTS = "HSAE_TFR_SUM_ACCOUNTS";

        /**
         * 账务批次
         */
        public static final String TFR_BATCH_ACCOUNTS = "HSAE_TFR_BATCH_EVENTS";

        /**
         * 账务数据表
         */
        public static final String TFR_EVENTS_ACCOUNTS = "HSAE_TFR_EVENTS";

        /**
         * 回传信息表
         */
        public static final String HSAE_TFR_DELIVERY_RECORDS = "HSAE_TFR_DELIVERY_RECORDS";

    }

    /**
     * 表字段
     */
    public static class TableField{

        /**
         * 账务明细表
         */
        public static class TfrDtlAccounts{

            /**
             * 账务明细主键
             */
            public static final String TFR_DTL_ACCOUNT_ID = "TFR_DTL_ACCOUNT_ID";
        }

        /**
         * 账务汇总表
         */
        public static class TfrSumAccounts{

            /**
             * 账务汇总主键
             */
            public static final String TFR_SUM_ACCOUNT_ID = "TFR_SUM_ACCOUNT_ID";
        }

        /**
         * 账务批次
         */
        public static class TfrBatchAccounts{

            /**
             * 账务批次主键
             */
            public static final String TFR_EVENT_BATCH_ID = "TFR_EVENT_BATCH_ID";
        }

        /**
         * 账务数据
         */
        public static class TfrEventsAccounts{

            /**
             * 账务数据主键
             */
            public static final String TFR_EVENT_ID = "TFR_EVENT_ID";
        }

        /**
         * 账务数据
         */
        public static class TfrDeliveryRecords{

            /**
             * 账务数据主键
             */
            public static final String RECORD_ID = "RECORD_ID";

            /**
             * 是否已经入账
             */
            public static final String ACCOUNTING_FLAG = "ACCOUNTING_FLAG";

            /**
             * 凭证ID
             */
            public static final String CERTIFICATE_ID = "CERTIFICATE_ID";

            /**
             * 凭证编号
             */
            public static final String CERTIFICATE_NUM = "CERTIFICATE_NUM";

            /**
             * 是否已经REMARKS
             */
            public static final String ACCOUNTING_REMARKS = "ACCOUNTING_REMARKS";
        }

        /**
         * 入账状态
         */
        public static final String ACCOUNTING_STATUS = "ACCOUNTING_STATUS";

        /**
         * 入账备注
         */
        public static final String ACCOUNTING_REMARKS = "ACCOUNTING_REMARKS";
    }

    /**
     * 回写状态
     */
    public static class ProcessFlag{

        /**
         * 回写完成
         */
        public static final String S = "S";

        /**
         * 已回写
         */
        public static final String Y = "Y";

        /**
         * 未回写
         */
        public static final String N = "N";
    }

    /**
     * 事件定义配置缓存获取主键
     */
    public static class ConfigRedisKey {

        /**
         * 配置前缀
         */
        public static final String PREFIX = "hscs:hsae:config:BATCH:";

        /**
         * 财务表定义
         */
        public static final String ACCOUNT = "account";

        /**
         * 事件分类
         */
        public static final String CATEGORY = "category";

        /**
         * 事件规则
         */
        public static final String RULE = "rule";

        /**
         * 事件映射
         */
        public static final String MAPPING = "mapping";

        /**
         * 筛选组
         */
        public static final String FILTER = "filter";

        /**
         * 事件来源
         */
        public static final String SOURCE = "source";

        /**
         * 子事件
         */
        public static final String EVENT = "event";
    }

    /**
     * 时间类型正则表达式
     */
    public static class MaskCodeRegex{

        /**
         * yyyy
         */
        public static final String YYYY = "[1-9]\\d{3}";

        /**
         * yyyy-MM
         */
        public static final String YYYYMM = "^[1-9]\\d{3}-(([0][1-9])|([1][0-2]))$";


        /**
         * yyyy-MM-dd
         */
        public static final String YYYYMMDD = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|\n" +
                "((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|\n" +
                "((0[48]|[2468][048]|[3579][26])00))-02-29)$";


        /**
         * yyyy-MM-dd HH:mm:ss
         */
        public static final String YYYYMMDD_HHMMSS = "((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|\n" +
                "((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|\n" +
                "((0[48]|[2468][048]|[3579][26])00))-02-29))\n" +
                "\\s([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";

        /**
         * yyyy/MM/dd
         */
        public static final String SPLASH_YYYYMMDD = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})\\/(((0[13578]|1[02])\\/(0[1-9]|[12][0-9]|3[01]))|\n" +
                "((0[469]|11)\\/(0[1-9]|[12][0-9]|30))|(02\\/(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|\n" +
                "((0[48]|[2468][048]|[3579][26])00))\\/02\\/29)$";

    }

    /**
     * 汇总类型
     */
    public static class SummaryType{

        /**
         * 不汇总
         */
        public static final String NO_SUMMARY = "NO_SUMMARY";

        /**
         * 	一次汇总
         */
        public static final String FIRST_SUMMARY = "FIRST_SUMMARY";

        /**
         * 二次汇总
         */
        public static final String SECOND_SUMMARY = "SECOND_SUMMARY";

        /**
         * Y	三次汇总
         */
        public static final String THIRD_SUMMARY = "THIRD_SUMMARY";
    }

    /**
     * 汇总阶段
     */
    public static class SummaryStage{

        /**
         * 一次汇总
         */
        public static final String FIRST_SUMMARY = "FIRST_SUMMARY";

        /**
         * 	二次汇总
         */
        public static final String SECOND_SUMMARY = "SECOND_SUMMARY";

        /**
         * 汇总完成
         */
        public static final String SUMMARY_COMPLETED = "SUMMARY_COMPLETED";
    }

    /**
     * RedisLock常量
     */
    public static class RedisLockObject {

        public static final String HSAE_AE_REDIS_LOCK_PREFIX = "hscs:lock:";

        /**
         * 外围数据key前缀
         */
        public static final String HSAE_AE_TFR_INTERFACE_KEY = "hscs:lock:hsae_tfr_interface:tfrInterfaceId:";


        /**
         * 财务汇总帐表key前缀
         */
        public static final String HSAE_AE_TFR_SUM_ACCOUNTS_KEY = "hscs:lock:hsae_tfr_sum_accounts:tfrSumAccountId:";

        /**
         * 事件表key前缀
         */
        public static final String HSAE_AE_TFR_BATCH_EVENTS_KEY = "hscs:lock:hsae_tfr_batch_events:tfrEventBatchId:";

        /**
         * 子事件表key前缀
         */
        public static final String HSAE_AE_TFR_EVENTS_KEY = "hscs:lock:hsae_tfr_events:tfrEventId:";

        /**
         * 账务历史记录表前缀
         */
        public static final String HSAE_AE_TFR_RECORDS_KEY = "hsae:lock:hsae_tfr_delivery_records:recordId:";
    }

    /**
     * 编码规则
     */
    public static class RuleCode {
        

        /**
         * 批次号编码规则
         */
        public static final String AE_CODE_RULE="HSAE.AE.BATCH_NUM_CODE";


        /**
         * 账务推送生成批次规则
         */
        public static final String HSAE_DELIVERY_ACCOUNT_CODE = "HSAE.TFR.DELIVERY_ACCOUNT_CODE";

        /**
         *
         */
        public static final String HSAE_TFR_DELIVERY_CLEARING_NO = "HSAE.TFR.DELIVERY_CLEARING_NO";

        /**
         * 汇总账务编码规则
         */
        public static final String HSAE_TFR_SUM_ACCOUNT_NUM = "HSAE.ACCOUNT.SUM_ACCOUNT_NUM";

        /**
         * 创建账务临时汇总规则
         */
        public static final String HSAE_TFR_TFR_SUM_TEMP_ID = "HSAE.TFR.TFR_SUM_TEMP_ID";

    }

    /**
     * 快码
     */
    public static class Code {

        public static final String HSAE_TFREVENT_ACCOUNT_STATUS ="HSAE.ACCOUNT_STATUS";

        public static final String HSAE_FEEDBACK_DELIVERY_STATUS = "HSAE.ACCT_TRF.DELIVERY_STATUS";

        public static final String HSPM_YES_NO = "HSPM.YES_NO";

        public static final String HSPM_SOURCE_SYS = "HSPM.SOURCE_SYS";

        public static final String HSAE_ACCOUNT_VALUE_TYPE = "HSAE.ACCOUNT.VALUE_TYPE";

        public static final String HSAE_ACCOUNT_ACCOUNT_LEVER = "HSAE.ACCOUNT.ACCOUNT_LEVER";

        public static final String HSAE_ACCOUNT_SUMMARY_METHOD = "HSAE.ACCOUNT.SUMMARY_METHOD";

        public static final String HSAE_ACCOUNT_SUMMARY_TYPE = "HSAE.ACCOUNT.SUMMARY_TYPE";

        public static final String HSAE_MAPPING_COLUMN_TYPE = "HSAE.MAPPING.COLUMN_TYPE";

        public static final String HSAE_RULE_VALUE_MODE = "HSAE.RULE.VALUE_MODE";

        public static final String HSAE_RULE_NUMBER_SIGN = "HSAE.RULE.NUMBER_SIGN";

        public static final String HSAE_RULE_DATE_FORMAT ="HSAE.RULE.DATE_FORMAT";

        public static final String HSAE_BATCH_ACC_TABLE = "HSAE.BATCH.ACC_TABLE";

        public static final String HSAE_EVENT_COMMON_VALUE = "HSAE.EVENT.COMMON_VALUE";

        public static final String HSAE_FEEDBACK_RPOCESS_FLAG = "HSAE.FEEDBACK.RPOCESS_FLAG";

        public static final String HSAE_IMPORT_DATA_IMPORT_METHOD = "HSAE.IMPOPT.SOURCE_METHOD";

        public static final String HSAE_ACCT_TRF_VALUE_TYPE = "HSAE.ACCT_TRF.VALUE_TYPE";

        public static final String HSAE_TFR_PROGRAM_TYPE = "HSAE.TFR.PROGRAM_TYPE";

        public static final String HSAE_ACCOUNT_FIELD_ATTRIBUTE="HSAE.ACCOUNT.FIELD_ATTRIBUTE";

        public static final String HSAE_DELIVERY_ACCOUNT_STATUS = "HSAE.DELIVERY.ACCOUNT_STATUS";
    }

    /**
     * Lov
     */
    public static class Lov {

        public static final String HSPM_VALUE_SET_COLLECT = "HSPM_VALUE_SET_COLLECT";

        public static final String HSPM_CODE_RULE_COLLECT = "HSPM_CODE_RULE_COLLECT";

        public static final String HSAE_ACCOUNT_NAME = "HSAE_ACCOUNT_NAME";

        public static final String HPFM_LOV_VIEW = "HPFM_LOV_VIEW";

        public static final String HSAE_EVENT_EXPANSION_MDM="HSAE_EVENT_EXPANSION_MDM";

    }

    public static class Cache{

        public static final String COLON = ":";
        public static final String LOV_SQL_VALUE = "hscs:hsae:lov:mapping:";
    }

    /**
     * 配置维护管理
     */
    public static class Profile{

        /**
         * 缓存过期时间配置代码
         */
        public static final String MULTI_EXPIRE = "HSPM.MULTI_REDIS_EXPIRE";

        /**
         * 是否立马回写入账状态
         */
        public static final String IMMEDIATELY_FEEDBACK = "HSAE.IM_ROLLBACK_ENABLE_FLAG";

        /**
         * 是否回滚进行备份
         */
        public static final String ROLLBACK_ACCOUNTS_BACKUP = "HSAE.IS_ROLLBACK_BACKUP_FLAG";

        /**
         * 创建账务最大限置
         */
        public static final String CREATE_ACCOUNT_MAX_LIMIT = "HSAE.CREATE_ACCOUNT_MAX_LIMIT";

        /**
         * 逆向创建账务最大限制
         */
        public static final String CREATE_REVERSE_ACCOUNT_MAX_LIMIT = "HSAE.REV_ACCOUNT_MAX_LIMIT";
    }

    public static class MappingCache{

        public static final String COLON = ":";


        public static final String LOV_SQL_VALUE = "hscs:hsae:lov:mapping:";
    }

    /**
     * 导入方式
     */
    public static final String INTERFACE_IMPORT = "INTERFACE_IMPORT";
}
