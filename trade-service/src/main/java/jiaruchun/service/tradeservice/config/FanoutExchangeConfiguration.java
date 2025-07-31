package jiaruchun.service.tradeservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiaruchun
 * @date 2025-07-28 14:01
 */

@Configuration
public class FanoutExchangeConfiguration {

    @Bean
    public FanoutExchange KillfanoutExchange1(){
        return new FanoutExchange("mall.createOrder.fanoutExchange");
    }

    @Bean
    public Queue queue1(){
        return new Queue("mall.CreateOrder.fanoutExchange.queue1");
    }
    /*
    * 绑定
    * */
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue1()).to(KillfanoutExchange1());
    }

}
