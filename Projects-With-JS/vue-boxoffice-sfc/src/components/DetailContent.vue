<template>
    <v-card-text>
        <div class="detail-thumbnail-img">
            <img v-bind:src="movieInfo.imgurl" v-bind:alt="movieInfo.movieNm">
        </div>
        <ul class="detail-list" v-if="Object.keys(movieInfo).length > 0">
            <li class="detail-movieNm">{{movieInfo.movieNm}} ({{movieInfo.prdtYear}})</li>
            <li><span class="detail-list-subtitle">영제 </span> {{movieInfo.movieNmEn}}</li>
            <li><span class="detail-list-subtitle">개봉연도 </span> {{movieInfo.openDt}}</li>
            <li><span class="detail-list-subtitle">제작국가 </span>
                <span
                    v-for="nation in movieInfo.nations"
                    v-bind:key="movieInfo.nations.indexOf(nation)"
                >
                    {{nation.nationNm}}
                </span>
            </li>
            <li><span class="detail-list-subtitle">상영시간 </span> {{movieInfo.showTm}}분</li>
            <li><span class="detail-list-subtitle">감독 </span>
                <v-chip outlined
                    v-for="director in movieInfo.directors"
                    v-bind:key="movieInfo.directors.indexOf(director)"
                >
                    {{ director.peopleNm }}({{ director.peopleNmEn }})
                </v-chip>
            </li>
            <li><span class="detail-list-subtitle">배우 </span>
                <v-chip outlined
                    v-for="actor in movieInfo.actors"
                    v-bind:key="movieInfo.actors.indexOf(actor)"
                >
                    {{ actor.peopleNm }}
                </v-chip>
                <span v-if="movieInfo.actors.length === 0">배우 정보가 없습니다</span>
            </li>
        </ul>
    </v-card-text>
</template>

<script>
export default {
  props: {
    movieInfo: Object,
  },
};
</script>

<style scoped>
.detail-thumbnail-img {
    margin-top: 15px;
    text-align: center;
}

.detail-movieNm {
    font-size: x-large;
    font-weight: bold;
    margin-bottom: 10px;
}

ul.detail-list {
    list-style: none;
    padding: 15px;
}

ul.detail-list > li {
    padding: 5px 0;
}

span.detail-list-subtitle {
    font-weight: 800;
    margin-right: 8px;
}
</style>
