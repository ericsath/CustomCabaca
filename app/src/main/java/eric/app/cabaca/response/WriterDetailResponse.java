package eric.app.cabaca.response;

import java.util.List;

public class WriterDetailResponse {
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
            public String name;
            public String username;
            public String photo_url;
            public String email;
            public String phone;
            public String link_user;
            public String deskripsi;
            public String gender;
            public String birthday;
            public Integer count_follower;
            public Integer count_following;
            public List<Karya> karya;
            public class Karya{
                    public String title;
                    public String cover_url;
                    public String rate_sum;
                    public Integer view_count;
                    public Integer id;

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

                public String getCover_url() {
                    return cover_url;
                }

                public void setCover_url(String cover_url) {
                    this.cover_url = cover_url;
                }

                public String getRate_sum() {
                    return rate_sum;
                }

                public void setRate_sum(String rate_sum) {
                    this.rate_sum = rate_sum;
                }

                public Integer getView_count() {
                    return view_count;
                }

                public void setView_count(Integer view_count) {
                    this.view_count = view_count;
                }
            }
            public List<Reading> reading_list;
            public class Reading{
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

                public String getRate_sum() {
                    return rate_sum;
                }

                public void setRate_sum(String rate_sum) {
                    this.rate_sum = rate_sum;
                }

                public Integer getView_count() {
                    return view_count;
                }

                public void setView_count(Integer view_count) {
                    this.view_count = view_count;
                }

                public String title;
                public String cover_url;
                public String rate_sum;
                public Integer view_count;
                public Integer id;

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }
            }

            public List<Reading> getReading() {
                return reading_list;
            }

            public void setReading(List<Reading> reading) {
                this.reading_list = reading;
            }

            public List<Karya> getKarya() {
                return karya;
            }

            public void setKarya(List<Karya> karya) {
                this.karya = karya;
            }

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

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getLink_user() {
                return link_user;
            }

            public void setLink_user(String link_user) {
                this.link_user = link_user;
            }

            public String getDeskripsi() {
                return deskripsi;
            }

            public void setDeskripsi(String deskripsi) {
                this.deskripsi = deskripsi;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public Integer getCount_follower() {
                return count_follower;
            }

            public void setCount_follower(Integer count_follower) {
                this.count_follower = count_follower;
            }

            public Integer getCount_following() {
                return count_following;
            }

            public void setCount_following(Integer count_following) {
                this.count_following = count_following;
            }
        }
}
