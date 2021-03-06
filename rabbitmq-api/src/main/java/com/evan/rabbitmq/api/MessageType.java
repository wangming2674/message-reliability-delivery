package com.evan.rabbitmq.api;

/**
 * 消息类型
 *
 * @author evan
 * @date 2022-03-08
 */
public class MessageType {
    /**
     * 迅速消息：不需要保障消息的可靠性, 也不需要做confirm确认
     */
    public static final String RAPID = "0";

    /**
     * 确认消息：不需要保障消息的可靠性，但是会做消息的confirm确认
     */
    public static final String CONFIRM = "1";

    /**
     * 可靠性消息： 一定要保障消息的100%可靠性投递，不允许有任何消息的丢失
     * PS: 保障数据库和所发的消息是原子性的（通过补偿，实现mq和数据库是 最终一致的）
     */
    public static final String RELIANT = "2";
}
