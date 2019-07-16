/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { GarantieService } from 'app/entities/garantie/garantie.service';
import { IGarantie, Garantie } from 'app/shared/model/garantie.model';

describe('Service Tests', () => {
  describe('Garantie Service', () => {
    let injector: TestBed;
    let service: GarantieService;
    let httpMock: HttpTestingController;
    let elemDefault: IGarantie;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(GarantieService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Garantie(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        false,
        false,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a Garantie', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new Garantie(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Garantie', async () => {
        const returnedFromService = Object.assign(
          {
            libelle: 'BBBBBB',
            libelleselection: 'BBBBBB',
            precisionlibelleselection: 'BBBBBB',
            tooltip: 'BBBBBB',
            reference: 'BBBBBB',
            choisieParDefaut: true,
            modifiable: true,
            visible: true,
            numeroOrdre: 1,
            filtreIdentifiant: 'BBBBBB',
            typeSpecificite: 'BBBBBB',
            libelleRisque: 'BBBBBB',
            groupementTarifaire: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of Garantie', async () => {
        const returnedFromService = Object.assign(
          {
            libelle: 'BBBBBB',
            libelleselection: 'BBBBBB',
            precisionlibelleselection: 'BBBBBB',
            tooltip: 'BBBBBB',
            reference: 'BBBBBB',
            choisieParDefaut: true,
            modifiable: true,
            visible: true,
            numeroOrdre: 1,
            filtreIdentifiant: 'BBBBBB',
            typeSpecificite: 'BBBBBB',
            libelleRisque: 'BBBBBB',
            groupementTarifaire: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Garantie', async () => {
        const rxPromise = service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
