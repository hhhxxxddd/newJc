package com.jinchi.common.config;

import com.jinchi.common.constant.AddressEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author：XudongHu
 * @className:WebMvcConfiguration
 * @description: SpringBoot配置文件
 * @date:14:31 2019/1/16
 * @Modifer:
 */

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/resources/",
            "classpath:/static/"};
    //    @Value("111")
//    private String address;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //设备档案
    registry.addResourceHandler("/equipmentArchiveRecord/pdf/**")
                    .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS)
                    .addResourceLocations("file:" + AddressEnum.getCurrentPath(AddressEnum.EQUIPMENT_ARCHIVE_ABS_ADDRESS.getCode()));

            registry.addResourceHandler("/equipmentInstructor/pic/**")
                    .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS)
                    .addResourceLocations("file:" +AddressEnum.getCurrentPath(AddressEnum.EQUIPMENT_INSTRUCTOR_ABS_ADDRESS.getCode()));

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/.well-known/pki-validation/**").addResourceLocations("classpath:/static/.well-known/pki-validation/");

       // registry.addResourceHandler("/myTest/**").addResourceLocations("file:"+absolutePath+"/myTest/");

        registry.addResourceHandler("/spotCheck/model/**").addResourceLocations("file:"+ AddressEnum.getCurrentPath(AddressEnum.DEVICE_SPOTCHECK_PHOTO.getCode()));

        registry.addResourceHandler("/spotCheck/record/**").addResourceLocations("file:"+ AddressEnum.getCurrentPath(AddressEnum.DEVICE_SPOTCHECK_RECORD.getCode()) );

        registry.addResourceHandler("/excel/repair/**").addResourceLocations("file:"+ AddressEnum.getCurrentPath(AddressEnum.EXCEL_REPAIR.getCode()) );

        registry.addResourceHandler("/excel/fireMage/**").addResourceLocations("file:"+ AddressEnum.getCurrentPath(AddressEnum.EXCEL_FIRE_MAGE.getCode()) );

        super.addResourceHandlers(registry);

    }
}
