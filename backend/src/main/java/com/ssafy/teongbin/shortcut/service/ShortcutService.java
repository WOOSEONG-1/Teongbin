package com.ssafy.teongbin.shortcut.service;

import com.ssafy.teongbin.shortcut.dto.request.AddShortcutRequestDto;
import com.ssafy.teongbin.shortcut.dto.request.DeleteShortcutRequestDto;
import com.ssafy.teongbin.shortcut.dto.response.ShortcutResponseDto;
import com.ssafy.teongbin.shortcut.entity.Shortcut;
import com.ssafy.teongbin.shortcut.repository.ShortcutRepository;
import com.ssafy.teongbin.user.entity.User;
import com.ssafy.teongbin.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShortcutService {
    private final ShortcutRepository shortcutRepository;
    private final UserService userService;

    // 숏컷 등록
    @Transactional
    public String addShortcut(AddShortcutRequestDto addShortcutRequestDto) {

        //  숏컷에 유저 정보 나중에 인증 받고 넣어야함.
        User user = new User();
        user.setEmail("asdfasd");
        user.setPassword("Asdfasdf");
        userService.join(user);

        Shortcut shortcut = Shortcut.builder()
                .user(user)
                .nickname(addShortcutRequestDto.getNickname())
                .location(addShortcutRequestDto.getLocation())
                .zoom_level(addShortcutRequestDto.getZoom_level())
                .build();

        shortcutRepository.save(shortcut);
        // String말고. 삭제는 잘 되었는지, 오류는 어떤건지 정보 담아서 넘겨야함.
        return "숏컷 등록 성공";
    }

    // 숏컷 삭제
    @Transactional
    public String deleteShortcut(DeleteShortcutRequestDto deleteShortcutRequestDto) {
        Optional<Shortcut> shortcutOptional = shortcutRepository.findById(deleteShortcutRequestDto.getId());
        // 나중에 예외 처리 추가하자.
        if (shortcutOptional.isPresent()) {
            shortcutRepository.deleteById(deleteShortcutRequestDto.getId());
            return "숏컷 삭제 성공";
        } else {
            return "해당 ID의 숏컷을 찾을 수 없습니다.";
        }
    }


    // 숏컷 리스트
    public List<ShortcutResponseDto> getShortcutList() {
        // 이거 나중에 사용자 인증 정보로 받으면 됨.
        List<Shortcut> shortcutList = shortcutRepository.findByUserId(5L);

        List<ShortcutResponseDto> shortcutResponseDtoList = shortcutList.stream()
                .map(shortcut ->
                        ShortcutResponseDto
                                .fromEntity(
                                        shortcut.getNickname(),
                                        shortcut.getLocation(),
                                        shortcut.getZoom_level())
                )
                .toList();

        return shortcutResponseDtoList;
    }

}
