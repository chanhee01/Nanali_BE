package Nanali.service;

import Nanali.domain.Member.Style;
import Nanali.domain.cody.cloth.Outfit;
import Nanali.domain.cody.cloth.Sex;
import Nanali.dtos.weather.OutfitWeatherRequest;
import Nanali.repository.OutfitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OutfitService {

    private final OutfitRepository outfitRepository;
    private final S3FileService s3FileService;

    @Transactional
    public Outfit save(MultipartFile OutfitImg, Style style, Sex sex, OutfitWeatherRequest weather) {

        String imgName = "";
        String imgUrl = "";
        Map<String, String> result = s3FileService.upload(OutfitImg, "outfit");
        String s3FileName = result.get("s3FileName");
        String s3Url = result.get("s3Url");
        imgName = s3FileName;
        imgUrl = s3Url;

        Outfit outfit = new Outfit(imgName, imgUrl, style, sex, weather.getTempFrom(), weather.getTempTo(),
                weather.getUvFrom(), weather.getUvTo(), weather.getRainFrom(), weather.getRainTo());

        Outfit savedOutfit = outfitRepository.save(outfit);

        return savedOutfit;
    }

    public Outfit findOutfit(Double temp, Double uv, Double rain, Style style, Sex sex) {
        // Style 타입으로 수정 필요
        List<Outfit> allOutifs = outfitRepository.findAllOutifs(temp, uv, rain, style, sex);

        Collections.shuffle(allOutifs);

        return allOutifs.get(0);
    }

    public Outfit findOne(Long id) {
        Outfit find = outfitRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        return find;
    }
}
