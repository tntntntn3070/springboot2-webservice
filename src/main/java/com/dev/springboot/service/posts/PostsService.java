package com.dev.springboot.service.posts;

import com.dev.springboot.domain.posts.Posts;
import com.dev.springboot.domain.posts.PostsRepository;
import com.dev.springboot.web.dto.PostsListResponseDto;
import com.dev.springboot.web.dto.PostsResponseDto;
import com.dev.springboot.web.dto.PostsSaveRequestDto;
import com.dev.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/*
 * RequiredArgsConstructor
 *  - final 이 선언된 모든 필드를 인자값으로 하는 생성자를 생성 (롬복)
 *  - Autowired 로 Bean 를 주입하지 않아도 됨
 */
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){

        /*
         * <Dirty checking (더티 체킹)>

         * 트랜잭션 안에서 DB 에서 데이터를 가져오면 해당 데이터는 영속성 컨텍스트가 유지된 상태
         *  - 영속성 컨텍스트 : 엔티티를 영구 저장하는 환경
         * 영속성 컨텍스트가 유지된 상태에서 데이터의 값을 변경하면
         * 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영함
         * 즉, Entity 객체의 값만 변경하면 별도로 Update 쿼리를 날릴 필요가 없음
         */
        Posts posts = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post does not exist. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post does not exist. id=" +id));

        return new PostsResponseDto(entity);
    }

    /*
     * readOnly = true 는 트랜잭션은 유지하되 죄회 기능만 남겨두어 조회 속도가 개선
     */
//    @Transactional(readOnly = true)
    @Transactional
    public List<PostsListResponseDto> findAllDesc(){
        return postRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id= " + id));

        postRepository.delete(posts);
    }

}
