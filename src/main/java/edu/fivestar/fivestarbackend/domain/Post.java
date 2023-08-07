package edu.fivestar.fivestarbackend.domain;

import edu.fivestar.fivestarbackend.dto.PostCreateReqDto;
import edu.fivestar.fivestarbackend.dto.PostUpdateReqDto;
import edu.fivestar.fivestarbackend.dto.UserPostGetResDto;
import edu.fivestar.fivestarbackend.util.BaseTimeEntity;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;
    private String imageURL;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;

    public Post(PostCreateReqDto dto, User user) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.user = user;
        this.imageURL = dto.getImageURL();
    }

    public UserPostGetResDto createUserPostGetResDto() {
        return new UserPostGetResDto(this.id, this.title, this.content, this.getCreatedAt(), this.getModifiedAt());
    }

    public void update(PostUpdateReqDto dto) {
        if (dto.getTitle() != null && !dto.getTitle().isEmpty()) {
            this.title = dto.getTitle();
        }
        this.content = dto.getContent();
    }
}