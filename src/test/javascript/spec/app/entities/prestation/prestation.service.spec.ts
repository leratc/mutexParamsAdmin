/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { PrestationService } from 'app/entities/prestation/prestation.service';
import { IPrestation, Prestation } from 'app/shared/model/prestation.model';

describe('Service Tests', () => {
  describe('Prestation Service', () => {
    let injector: TestBed;
    let service: PrestationService;
    let httpMock: HttpTestingController;
    let elemDefault: IPrestation;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(PrestationService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Prestation(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', false, 0);
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

      it('should create a Prestation', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new Prestation(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Prestation', async () => {
        const returnedFromService = Object.assign(
          {
            libelle: 'BBBBBB',
            formuleLibelle: 'BBBBBB',
            formule: 'BBBBBB',
            formuleApplication: 'BBBBBB',
            epingleGarantie: true,
            numeroOrdre: 1
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

      it('should return a list of Prestation', async () => {
        const returnedFromService = Object.assign(
          {
            libelle: 'BBBBBB',
            formuleLibelle: 'BBBBBB',
            formule: 'BBBBBB',
            formuleApplication: 'BBBBBB',
            epingleGarantie: true,
            numeroOrdre: 1
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

      it('should delete a Prestation', async () => {
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
