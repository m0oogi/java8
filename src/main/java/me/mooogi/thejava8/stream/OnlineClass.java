package me.mooogi.thejava8.stream;

import java.util.Optional;
import me.mooogi.thejava8.optional.Progress;

public class OnlineClass {

    private Integer id;
    private String title;
    private boolean closed;

    private Progress progress;

    public Optional<Progress> getProgress() {
        return Optional.ofNullable(progress);
    }

    //리턴타입에만 사용하는 이유 (파라미터에 사용할때 왜 귀찮은가)
    public void setProgress(Optional<Progress> progress) {
        //이것도 위험함
        //null에 대고 ifPresent하면 결국 터짐
        //쓰는의미가없음
        progress.ifPresent(value -> this.progress = value);
    }

    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    @Override
    public String toString() {
        return "OnlineClass{" + "id=" + id + ", title='" + title + '\'' + ", closed=" + closed + '}';
    }
}
