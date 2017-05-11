package transaction.converter;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalConverter;
import org.springframework.core.convert.converter.Converter;

public final class EnumToCodeConverter implements Converter<Enum<?>, String>, ConditionalConverter {

	@Override
	public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
		return EnumCodeGetter.class.isAssignableFrom(targetType.getType());
	}

	@Override
	public String convert(Enum<?> source) {
		if(source instanceof EnumCodeGetter) {
			return ((EnumCodeGetter)source).getCode();
		}
		
		return null;
	}

}
