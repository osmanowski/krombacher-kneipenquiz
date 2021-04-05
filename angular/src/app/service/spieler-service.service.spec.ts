import { TestBed } from '@angular/core/testing';

import { SpielerServiceService } from './spieler-service.service';

describe('SpielerServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SpielerServiceService = TestBed.get(SpielerServiceService);
    expect(service).toBeTruthy();
  });
});
