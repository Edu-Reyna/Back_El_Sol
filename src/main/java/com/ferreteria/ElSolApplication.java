package com.ferreteria;

import com.ferreteria.entities.*;
import com.ferreteria.repositories.ICategoryRepository;
import com.ferreteria.repositories.IProductRepository;
import com.ferreteria.repositories.IUserRepository;
import com.ferreteria.services.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableScheduling
public class ElSolApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElSolApplication.class, args);
	}

	@Bean
	CommandLineRunner init(IUserRepository userRepository, ICategoryRepository categoryRepository,
						   IProductRepository productRepository) {
		return args -> {
			PermissionEntity createUser = PermissionEntity.builder()
					.permissionEnum(PermissionEnum.CREATE_USER)
					.build();

			PermissionEntity readUser = PermissionEntity.builder()
					.permissionEnum(PermissionEnum.READ_USER)
					.build();

			PermissionEntity updateUser = PermissionEntity.builder()
					.permissionEnum(PermissionEnum.UPDATE_USER)
					.build();

			PermissionEntity deleteUser = PermissionEntity.builder()
					.permissionEnum(PermissionEnum.DELETE_USER)
					.build();

			PermissionEntity createSale = PermissionEntity.builder()
					.permissionEnum(PermissionEnum.CREATE_SALE)
					.build();

			PermissionEntity readSale = PermissionEntity.builder()
					.permissionEnum(PermissionEnum.READ_SALE)
					.build();

			PermissionEntity updateSale = PermissionEntity.builder()
					.permissionEnum(PermissionEnum.UPDATE_SALE)
					.build();

			PermissionEntity deleteSale = PermissionEntity.builder()
					.permissionEnum(PermissionEnum.DELETE_SALE)
					.build();

			PermissionEntity createProduct = PermissionEntity.builder()
					.permissionEnum(PermissionEnum.CREATE_PRODUCT)
					.build();

			PermissionEntity readProduct = PermissionEntity.builder()
					.permissionEnum(PermissionEnum.READ_PRODUCT)
					.build();

			PermissionEntity updateProduct = PermissionEntity.builder()
					.permissionEnum(PermissionEnum.UPDATE_PRODUCT)
					.build();

			PermissionEntity deleteProduct = PermissionEntity.builder()
					.permissionEnum(PermissionEnum.DELETE_PRODUCT)
					.build();

			RoleEntity employee = RoleEntity.builder()
					.roleEnum(RoleEnum.EMPLOYEE)
					.permissionList(Set.of(createSale, readSale, updateSale, updateProduct))
					.build();

			RoleEntity admin = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionList(Set.of(createUser, readUser, updateUser, deleteUser, createSale, readSale, updateSale, deleteSale, createProduct, readProduct, updateProduct, deleteProduct))
					.build();

			UserEntity employeeUser = UserEntity.builder()
					.username("Abdiel")
					.password("$2a$10$.r2Vq5O0fOkEFeXrTwi1w.q9.0D5OcPlm8.6V.qrO3uJN7EBPMAv2")
					.isEnabled(true)
					.accountNoLocked(true)
					.accountNoExpired(true)
					.credentialNoExpired(true)
					.roles(Set.of(employee))
					.build();

			UserEntity adminUser = UserEntity.builder()
					.username("Eduardo")
					.password("$2a$10$.r2Vq5O0fOkEFeXrTwi1w.q9.0D5OcPlm8.6V.qrO3uJN7EBPMAv2")
					.isEnabled(true)
					.accountNoLocked(true)
					.accountNoExpired(true)
					.credentialNoExpired(true)
					.roles(Set.of(admin))
					.build();

			userRepository.saveAll(List.of(employeeUser, adminUser));

			CategoryEntity herramientas = CategoryEntity.builder()
					.name("Herramientas")
					.description("Incluye herramientas manuales, eléctricas y de medición.")
					.build();

			CategoryEntity materiales = CategoryEntity.builder()
					.name("Materiales de Construcción")
					.description("Cemento, arena, ladrillos y otros materiales de obra.")
					.build();

			CategoryEntity electricidad = CategoryEntity.builder()
					.name("Electricidad")
					.description("Productos eléctricos como cables, luminarias y tableros.")
					.build();


			categoryRepository.saveAll(List.of(herramientas, materiales, electricidad));

			ProductEntity Martillo = ProductEntity.builder()
					.name("Martillo")
					.description("Martillo de acero con mango ergonómico.")
					.price(new BigDecimal("15.99"))
					.stock(50)
					.status(true)
					.category(herramientas)
					.build();

			ProductEntity taladro = ProductEntity.builder()
					.name("Taladro")
					.description("Taladro eléctrico con múltiples velocidades.")
					.price(new BigDecimal("79.99"))
					.stock(30)
					.status(true)
					.category(herramientas)
					.build();

			ProductEntity cemento = ProductEntity.builder()
					.name("Cemento Portland")
					.description("Saco de cemento de 50 kg.")
					.price(new BigDecimal("12.50"))
					.stock(200)
					.status(true)
					.category(materiales)
					.build();

			ProductEntity arena = ProductEntity.builder()
					.name("Arena Fina")
					.description("Saco de arena fina para construcción.")
					.price(new BigDecimal("7.25"))
					.stock(150)
					.status(true)
					.category(materiales)
					.build();

			ProductEntity bombilla = ProductEntity.builder()
					.name("Bombilla LED")
					.description("Bombilla LED de 10W, luz blanca.")
					.price(new BigDecimal("3.99"))
					.stock(200)
					.status(true)
					.category(electricidad)
					.build();

			ProductEntity cable = ProductEntity.builder()
					.name("Cable Eléctrico")
					.description("Cable eléctrico calibre 12, rollo de 50 metros.")
					.price(new BigDecimal("25.00"))
					.stock(75)
					.status(true)
					.category(electricidad)
					.build();

			productRepository.saveAll(List.of(Martillo, taladro, cemento, arena, bombilla, cable));
		};
	}

}
