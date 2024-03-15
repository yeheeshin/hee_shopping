package com.shopping.hee.service;

import com.shopping.hee.domain.Ask;
import com.shopping.hee.repository.AskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class AskService {
    private final AskRepository askRepository;

    public Ask saveAsk(Ask ask) {
        ask.setAdate(LocalDate.now());

        return askRepository.save(ask);
    }
}
