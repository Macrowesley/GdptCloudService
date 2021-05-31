package cc.mrbird.febs.common.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class SchedulerTask {

    /**
     * 定时检查订单是否过期
     */
    @Scheduled(cron="0 0 0/1 * * ?")
    @Transactional(propagation = Propagation.SUPPORTS)
    private void checkOrderIsExpire(){
        log.info("每隔一小时 —— ");
    }
}
