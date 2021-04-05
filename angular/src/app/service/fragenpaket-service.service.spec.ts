import { TestBed } from '@angular/core/testing';

import { FragenpaketServiceService } from './fragenpaket-service.service';

describe('FragenpaketServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FragenpaketServiceService = TestBed.get(FragenpaketServiceService);
    expect(service).toBeTruthy();
  });
});
