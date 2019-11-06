package eric.app.cabaca.response;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookIdResponse {
    public String success;
    public Result result;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

        public class Result{
            public String title;
            public String cover_url;
            public String status;
            public String decimal_rate;
            public String synopsis;
            public Writer_by_writer_id Writer_by_writer_id;
            public List<Genre> genres;
            public class Genre{
                public String title;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }
            public List<Reviews> reviews;
            public class Reviews{
                public String review;

                public Reviews.User_by_reviewer_id getUser_by_reviewer_id() {
                    return User_by_reviewer_id;
                }
                public String getReview() {
                    return review;
                }

                public void setReview(String review) {
                    this.review = review;
                }

                public void setUser_by_reviewer_id(Reviews.User_by_reviewer_id user_by_reviewer_id) {
                    User_by_reviewer_id = user_by_reviewer_id;
                }

                public User_by_reviewer_id User_by_reviewer_id;

                public class User_by_reviewer_id{
                    public String name;
                    public String photo_url;

                    public String getPhoto_url() {
                        return photo_url;
                    }

                    public void setPhoto_url(String photo_url) {
                        this.photo_url = photo_url;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }
                }
            }
            public List<Reviews> getReviews() {
                return reviews;
            }

            public void setReviews(List<Reviews> reviews) {
                this.reviews = reviews;
            }


            public String getSynopsis() {
                return synopsis;
            }

            public void setSynopsis(String synopsis) {
                this.synopsis = synopsis;
            }

            public List<Genre> getGenres() {
                return genres;
            }

            public void setGenres(List<Genre> genres) {
                this.genres = genres;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCover_url() {
                return cover_url;
            }

            public void setCover_url(String cover_url) {
                this.cover_url = cover_url;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDecimal_rate() {
                return decimal_rate;
            }

            public void setDecimal_rate(String decimal_rate) {
                this.decimal_rate = decimal_rate;
            }

            public Writer_by_writer_id getWriter_by_writer_id() {
                return Writer_by_writer_id;
            }

            public void setWriter_by_writer_id(Writer_by_writer_id writer_by_writer_id) {
                Writer_by_writer_id = writer_by_writer_id;
            }

                public class Writer_by_writer_id{
                    public User_by_user_id User_by_user_id;

                    public User_by_user_id getUser_by_user_id() {
                        return User_by_user_id;
                    }

                    public void setUser_by_user_id(User_by_user_id user_by_user_id) {
                        User_by_user_id = user_by_user_id;
                    }

                        public class User_by_user_id{
                            public String name;
                            public String username;
                            public String photo_url;

                            public String getName() {
                                return name;
                            }

                            public void setName(String name) {
                                this.name = name;
                            }

                            public String getUsername() {
                                return username;
                            }

                            public void setUsername(String username) {
                                this.username = username;
                            }

                            public String getPhoto_url() {
                                return photo_url;
                            }

                            public void setPhoto_url(String photo_url) {
                                this.photo_url = photo_url;
                            }
                        }
                }

        }
}
