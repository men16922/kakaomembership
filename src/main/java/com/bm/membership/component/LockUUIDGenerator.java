package com.bm.membership.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

/**
 * packageName    : com.bm.membership.component
 * fileName       : LockUUIDGenerator
 * author         : men16
 * date           : 2022-06-23
 * description    : TID 생성 컴포넌트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-23        men16       최초 생성
 */
@Component
@Slf4j
public class LockUUIDGenerator {

    private static volatile AtomicLong sequence = new AtomicLong();
    private long MAX_SEQ_NUMBER = 999;

    private Object lock = new Object();

    protected DateTimeFormatter tidTimeFormat = null;


    @Value("${uuid.time-format}")
    protected String timeFormat = null;

    @Value("${uuid.seq-format}")
    protected String seqFormet = null;

    @Value("${uuid.unique-code}")
    protected String uniqueCode = null;

    protected DecimalFormat sequenceFormat = null;


    @PostConstruct
    public void initialize() {
        this.tidTimeFormat = DateTimeFormatter.ofPattern(this.timeFormat);
        String seqSize = this.seqFormet;

        if(seqSize.length() > 18) {
            seqSize = "000";
        }


        MAX_SEQ_NUMBER = (long) (Math.pow(10, seqSize.length()) -1);
        log.info("Sequence max value: {}", MAX_SEQ_NUMBER);
        sequenceFormat = new DecimalFormat(seqSize);

        tidTimeFormat = DateTimeFormatter.ofPattern(timeFormat);

        log.info("####################################################");

    }

    /**
     * 거래ID의 일련번호를 가져온다.
     * @return			일련번호
     */
    private String getSequence(){
        String seq = null;

        synchronized (lock) {
            if(MAX_SEQ_NUMBER <= sequence.get()) {
                sequence.set(0L);
            } else {
                sequence.incrementAndGet();
            }

            log.info("Return Seq: {}, MAX NUM: {}", sequence.get(), MAX_SEQ_NUMBER);
            seq = sequenceFormat.format(sequence.get());
        }

        return seq;
    }

    public String generateUUID() {
        LocalDateTime dateTime = LocalDateTime.now();

        return dateTime.format(tidTimeFormat) + getSequence() + uniqueCode;

    }


}

