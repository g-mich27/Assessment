package com.cibertec.assessment.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.assessment.beans.SquareBean;
import com.cibertec.assessment.model.Square;
import com.cibertec.assessment.repo.SquareRepo;
import com.cibertec.assessment.service.SquareService;

@Service
public class SquareServiceImpl implements SquareService{

	@Autowired 
	SquareRepo squareRepo;
	
//	Al momento de crear se debe validar si 
//	alguno de parte del cuadrado se encuentra dentro de algun
//	poligono y de ser asi se debe capturar el id de los poligonos y 
//	guardar como un string pero con formato de array
//	Ejemplo polygons = "["1","2"]"
//	Se guardan los ids correspondites
//	usar los metodos ya existentes para listar polygonos
	
	
	@Override
    public void create(Square s) {
        squareRepo.save(s);
    }

    @Override
    public void create(List<Square> sq) {
        squareRepo.saveAll(sq);
    }

    @Override
    public List<SquareBean> list() {
        List<Square> list = squareRepo.findAll();
        List<SquareBean> listSquareBeans = new ArrayList<>();
        list.forEach(s -> {
            Integer[] intArrayX = new Integer[4];
            Integer[] intArrayY = new Integer[4];

            convertStringInIntegerArray(s.getXPoints(), s.getYPoints(), intArrayX, intArrayY);

            SquareBean squareBean = new SquareBean();
            squareBean.setId(s.getId());
            squareBean.setName(s.getName());
            squareBean.setXPoints(intArrayX);
            squareBean.setYPoints(intArrayY);
            squareBean.setNpoints(s.getNpoints());
            squareBean.setPolygons(s.getPolygons());

            listSquareBeans.add(squareBean);
        });

        return listSquareBeans;
    }

    private void convertStringInIntegerArray(String xPoints, String yPoints, Integer[] intArrayX, Integer[] intArrayY) {

        String cleanedXPoints = xPoints.substring(1, xPoints.length() - 1);
        String cleanedYPoints = yPoints.substring(1, yPoints.length() - 1);

        String[] partsX = cleanedXPoints.split(", ");
        String[] partsY = cleanedYPoints.split(", ");

        for (int i = 0; i < partsX.length; i++) {
            intArrayX[i] = Integer.parseInt(partsX[i]);
        }

        for (int i = 0; i < partsY.length; i++) {
            intArrayY[i] = Integer.parseInt(partsY[i]);
        }
    }

	@Override
	public Square update(Square s) {
		return squareRepo.save(s);
	}

	@Override
	public void delete(int id) {
		squareRepo.deleteById(id);
	}
	
	
}
