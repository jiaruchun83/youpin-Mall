package jiaruchun.api.openfeignclient.item;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jiaruchun.api.pojo.dto.ItemDTO;
import jiaruchun.api.pojo.dto.OrderDetailDTO;

import java.util.Collection;
import java.util.List;

@FeignClient(value = "item-service")
public interface ItemOpenFeignApi {
    @GetMapping("/items")
    List<ItemDTO> queryItemByIds(@RequestParam("ids") Collection<Long> ids);

    @PutMapping("/items/stock/deduct")
    void deductStock(@RequestParam List<OrderDetailDTO> items);
}
