package com.ssafy.teongbin.shortcut.service;

import com.ssafy.teongbin.shortcut.dto.request.AddShortcutRequestDto;
import com.ssafy.teongbin.shortcut.dto.request.DeleteShortcutRequestDto;
import com.ssafy.teongbin.shortcut.dto.response.ShortcutResponseDto;
import com.ssafy.teongbin.shortcut.entity.Shortcut;
import com.ssafy.teongbin.shortcut.repository.ShortcutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShortcutService {
    private final ShortcutRepository shortcutRepository;

    // 숏컷 등록
    @Transactional
    public String addShortcut(AddShortcutRequestDto addShortcutRequestDto) {
        Shortcut shortcut = new Shortcut();

        // 숏컷에 유저 정보 나중에 인증 받고 넣어야함.
//        User user = new User();
//        ();

        shortcut.setNickname(addShortcutRequestDto.getNickname());
        shortcut.setLocation(addShortcutRequestDto.getLocation());
        shortcut.setZoom_level(addShortcutRequestDto.getZoom_level());
        // 날짜 시간 정보 추가.?
        shortcut.setCreated_at(LocalDateTime.now());
        shortcutRepository.save(shortcut);





        // String말고. 삭제는 잘 되었는지, 오류는 어떤건지 정보 담아서 넘겨야함.
        return "성공했습니다~";
    }

    // 숏컷 삭제
    @Transactional
    public void deleteShor6cut(DeleteShortcutRequestDto dto) {
        Optional<Shortcut> shortcut = shortcutRepository.findById(dto.getId());
        shortcutRepository.deleteById(shortcut.get().getId());
    }

    // 숏컷 리스트

}
