package stmall.infra;

import stmall.domain.*;
import stmall.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MypageViewHandler {

//<<< DDD / CQRS
    @Autowired
    private MypageRepository mypageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void when_then_CREATE_ (@Payload  ) {
        try {

            if (!.validate()) return;

            // view 객체 생성
            Mypage mypage = new Mypage();
            // view 객체에 이벤트의 Value 를 set 함
            mypage.setOrderId(.getId());
            mypage.setItemId(OrderPlaced.itemId);
            mypage.setCustomerId(OrderPlaced.customerId);
            mypage.setQty(OrderPlaced.qty);
            mypage.setAddress(OrderPlaced.address);
            mypage.setOrderStatus(ORDERPLACED);
            // view 레파지 토리에 save
            mypageRepository.save(mypage);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenDeliveryCompleted_then_UPDATE_1(@Payload DeliveryCompleted deliveryCompleted) {
        try {
            if (!deliveryCompleted.validate()) return;
                // view 객체 조회

                List<Mypage> mypageList = mypageRepository.findByOrderId(deliveryCompleted.getOrderId());
                for(Mypage mypage : mypageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    mypage.setDeliveryStatus(DELIVERYCOMPLETED);
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderCancelled_then_UPDATE_2(@Payload OrderCancelled orderCancelled) {
        try {
            if (!orderCancelled.validate()) return;
                // view 객체 조회

                List<Mypage> mypageList = mypageRepository.findByOrderId(orderCancelled.getId());
                for(Mypage mypage : mypageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenDeliveryReturned_then_UPDATE_3(@Payload DeliveryReturned deliveryReturned) {
        try {
            if (!deliveryReturned.validate()) return;
                // view 객체 조회

                List<Mypage> mypageList = mypageRepository.findByOrderId(deliveryReturned.getOrderId());
                for(Mypage mypage : mypageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


//>>> DDD / CQRS
}

